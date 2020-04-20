package cn.jt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//注解的作用目标     作用在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//注解在运行期工作
public @interface CacheFind {
    //注解中的方法即属性
    String key() default "";
    int seconds() default 0;//0用户不需要定义超时时间
}
