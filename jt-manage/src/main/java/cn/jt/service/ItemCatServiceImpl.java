package cn.jt.service;

import cn.jt.annotation.CacheFind;
import cn.jt.mapper.ItemCatMapper;
import cn.jt.mapper.ItemMapper;
import cn.jt.pojo.ItemCat;
import cn.jt.util.ObjectMapperUtil;
import cn.jt.vo.EasyUITree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl  implements  ItemCatService{

    @Autowired(required = false)//懒加载
     ShardedJedis jedis;
    //Jedis jedis;


    @Autowired
    ItemCatMapper itemCatMapper;


    @Override
    public String findItemCatNameById(int itemCatId) {
        String name = itemCatMapper.selectById(itemCatId).getName();
       // System.out.println("name"+name);
        return name;
    }

    /*
        根据父级ID查询商品分类信息
     */
    @Override
    @CacheFind //该方法需要使用AOP
    public List<EasyUITree> findItemCatListById(int parentId) {
        //因为要将结果封装为EasyUITree 所以要先根据父类ID查询所有商品分类
        List<ItemCat> itemCatList=selectItemCatListByPatentId(parentId);
        //讲结果封装进vo集合中
        List<EasyUITree> easyUITrees=new ArrayList<EasyUITree>();
        for(ItemCat itemCat :itemCatList){
            Long id = itemCat.getId();
            String text = itemCat.getName();
              String state=  itemCat.getParent()?"closed":"open";
            EasyUITree eu=new EasyUITree(id,text,state);
            easyUITrees.add(eu);

        }
        return easyUITrees;
    }

    /*
        从redis中动态获取数据
        步骤:
     */
    @Override
    public List<EasyUITree> findItemCatCache(Integer parentId) {
        long start = System.currentTimeMillis();
        // 缓存的数据的key通过包名.类名.方法名::+parentId实现
            String key="cn.jt.service.ItemCatServiceImpl.findItemCatCache::"+parentId;
             //从缓存中查询数据
                String value = jedis.get(key);
                //定义公用返回值对象
        List<EasyUITree> treeList =new ArrayList<EasyUITree>();
                if(StringUtils.isEmpty(value)){//判断是否有数据
                    //如果缓存中没数据从数据库中获取
                    treeList = findItemCatListById(parentId);
                    long end = System.currentTimeMillis();
                    System.out.println("查询数据库时间为"+(start-end));

                    //将数据保存到Redis中
                    //将List数据转成JSON格式
                    String json = ObjectMapperUtil.toJson(treeList);
                    jedis.set(key,json);
                }else{
                    //如果缓存有数据就从缓存获取
                    treeList = ObjectMapperUtil.toObject(value, treeList.getClass());
                    long end = System.currentTimeMillis();
                    System.out.println("查询缓存时间为"+(start-end));

                }
                return treeList;
    }

    /*
        根据父类id查询数据库中所有Itemcat

     */
    private List<ItemCat> selectItemCatListByPatentId(int parentId) {
        QueryWrapper<ItemCat> qw =new QueryWrapper<>();
        qw.eq("parent_id",parentId);
      return   itemCatMapper.selectList(qw);
    }
}
