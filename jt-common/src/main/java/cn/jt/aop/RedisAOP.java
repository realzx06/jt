package cn.jt.aop;

import cn.jt.annotation.CacheFind;
import cn.jt.util.ObjectMapperUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import java.util.Arrays;

@Component//将类交给容器管理
@Aspect//标识切面类
public class RedisAOP {
    @Autowired(required = false)
    JedisCluster jedis;//集群方式
   // Jedis jedis;
   // ShardedJedis jedis;


    /*
        要求拦截ItemCatServiceImpl对象
     */

    //设置切入点 (使用bean 配置切入点表达式)
    @Pointcut("bean(itemCatServiceImpl)")
    public void pointCut(){

    }
    //定义前置通知
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("执行前置通知");
        Object target = joinPoint.getTarget();//获取目标对象
        Object[] args = joinPoint.getArgs();//获取动态参数
        String name = joinPoint.getSignature().getName();//获取目标方法名称
        //System.out.println(target.getClass());
        //System.out.println(args.getClass());
        //System.out.println(name);
    }

    //使用环绕通知记录方法执行时间
    //定义切入点       返回值 包名.任意包.任意类.任意方法(任意返回值)
    @Around("execution(* cn.jt.service..*.*(..))")
    public Object timeObject(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();
        try {
            //获取类名\方法名
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String method = joinPoint.getSignature().getName();

            Object obj = joinPoint.proceed();//执行方法
            long end = System.currentTimeMillis();
            System.out.println(className+"."+method+"用时"+(end-start)+"毫秒");
            System.out.println(obj);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }

    }


    //使用AOP完成商品分类信息缓存查询
    @Around("@annotation(cacheFind)")                //注解可以作为参数 必须放在第二位
    public Object cacheAround(ProceedingJoinPoint joinPoint, CacheFind cacheFind){
            //定义生成key
        String key = getKey(joinPoint, cacheFind);
            //根据key查询redis中是否有数据
        String value = jedis.get(key);
        //表示返回值对象
        Object obj=null;
        //哦按点是否有数据
        if(StringUtils.isEmpty(value)){
            System.out.println("执行数据库查询");
            try { //如果没数据则要从数据库中查询
                obj = joinPoint.proceed();
                //将数据保存到缓存中
                String json = ObjectMapperUtil.toJson(obj);
                //判断用户是有设有超时时间
                if(cacheFind.seconds()>0){
                    int seconds = cacheFind.seconds();
                    jedis.setex(key,seconds,json);//设置超时时间
                }else{
                    jedis.set(key,json);//不设置超时时间
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                throw new RuntimeException(throwable);
            }

        }else{//如果缓存中有数据 则直接从缓存中获取
            //要获取返回值类型
            System.out.println("执行缓存查询");
            Class targetClass = getReturnType(joinPoint);
           obj = ObjectMapperUtil.toObject(value, targetClass);
        }

        return obj;
    }


    //获取返回值类型方法
    public Class getReturnType(ProceedingJoinPoint joinPoint){
                //或者代理类执行的方法
            MethodSignature ms= (MethodSignature) joinPoint.getSignature();
        return ms.getReturnType();
    }
    //获取redis Key中的方法
    public  String  getKey(ProceedingJoinPoint joinPoint,CacheFind cacheFind){
            //获取用户中的key  key有可能是用户定义 有可能是自动生成
            //查看用户是否有自定义key
            if(!StringUtils.isEmpty(cacheFind.key())){
            return cacheFind.key();
            }else{
                //如果没定义 则根据规则生成 包名.类名.方法名::参数
              String className = joinPoint.getSignature().getDeclaringTypeName();
                String method = joinPoint.getSignature().getName();
                Object[] args = joinPoint.getArgs();
                System.out.println("参数列表"+ Arrays.toString(args));
                Object arg0=args[0];
                return className+"."+method+"::"+arg0;

            }
    }
}
