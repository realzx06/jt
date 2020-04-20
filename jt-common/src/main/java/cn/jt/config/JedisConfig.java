package cn.jt.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//标识配置类信息
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class JedisConfig {

    //引入Redis分片
    @Value("${redis.nodes}")
    private String nodes;

    @Value("${redis.sentinel}")
    private String sentinel;

    //使用Redis集群
    @Value("${redis.cluster}")
    private String cluster;

    //配置Redsi集群
    @Bean
    public JedisCluster jedisCluster(){

        Set<HostAndPort> nodes =new HashSet<>();
        //截取配置信息
        String[] nodeArr = cluster.split(",");
        for (String node:nodeArr) {
        String host=    node.split(":")[0];
        int port=Integer.parseInt(node.split(":")[1]);
            nodes.add(new HostAndPort(host,port));
        }

        JedisCluster jedisCluster=new JedisCluster(nodes);
        return jedisCluster;
    }

    //使用哨兵获取Jedis
   /* @Bean
    public Jedis sentinel(){
        Set<String> sent=new HashSet<>();
        sent.add(sentinel);
        JedisSentinelPool pool =new JedisSentinelPool("mymaster",sent);
        return pool.getResource();

    }*/


    //将ShardedJedis对象交给Spring管理
    @Bean
    public ShardedJedis shardedJedis(){
        List<JedisShardInfo> shards=new ArrayList<>();
        //创建3台redis分片实例
        String[] nodeArray = nodes.split(",");
        for (String node:nodeArray) {
            String host=node.split(":")[0];
            int port=Integer.parseInt(node.split(":")[1]);
            shards.add(new JedisShardInfo(host,port));
        }
        ShardedJedis sjedis=new ShardedJedis(shards);
       /* shards.add(new JedisShardInfo("192.168.126.166",6380));
        shards.add(new JedisShardInfo("192.168.126.166",6381));*/


        return sjedis;
      /*  sjedis.set("shards","测试redis分片集群");
        System.out.println(sjedis.get("shards"))
*/
    }


/*
    @Value("${redis.node}")
    private  String redisNode;
    //创建对象
    @Bean //将方法返回值交给spring管理 单例对象
    public Jedis jedis(){
        String[] split = redisNode.split(":");
        String host=split[0];
        System.out.println(host);
        int port=Integer.parseInt(split[1]);
        return new Jedis(host,port);
    }
    */


}
