package com.jt;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import java.util.*;


public class TestRedis {
    Jedis jedis;
    @BeforeEach
    public void init(){
        System.out.println(111);
        String host="192.168.126.166";
        int port=6379;
         jedis =new Jedis(host,port);
    }

    @Test
    public  void test01(){

        jedis.set("test","Hello Redis");
        String test = jedis.get("test");
        System.out.println(test);
    }

    /*
     * 需求 当key不存在时创建 存在不能修改
     */

    @Test
    public void test02(){
        //清空数据
        jedis.flushAll();
        jedis.setnx("a","123");
        jedis.setnx("a","456");
        System.out.println(jedis.get("a"));

    }

    /*
        redis添加记录 同时添加超时时间 ,并且展示剩余存活时间

     */
    @Test
    public void testSetEX(){

       // jedis.set("a","123456");
       // int a=1/0;异常
       // jedis.expire("a",60);//这里不能保存操作的原子性
        jedis.setex("b",10,"设置超时时间");//原子操作
        System.out.println(jedis.ttl("a"));//>0剩余超时时间 -1永不超时 -2没有该数据
    }

    @Test
    public void testSetNXEX() throws InterruptedException {

        SetParams params =new SetParams();
        params.nx().ex(10);
        jedis.set("t","测试超时时间方法",params);
        System.out.println(jedis.get("t"));//测试超时时间方法
        Thread.sleep(11000);
        System.out.println(jedis.get("t"));//null
    }

    @Test
    public  void testHash(){
        jedis.hset("user","id","101");
        jedis.hset("user","age","20");
        Map<String, String> user = jedis.hgetAll("user");
        System.out.println(user);//{age=20, id=101}
    }

    @Test
    public void testShards(){
        List<JedisShardInfo> shards=new ArrayList<>();
        //创建3台redis分片实例
        shards.add(new JedisShardInfo("192.168.126.166",6379));
        shards.add(new JedisShardInfo("192.168.126.166",6380));
        shards.add(new JedisShardInfo("192.168.126.166",6381));
        ShardedJedis sjedis=new ShardedJedis(shards);
        sjedis.set("shards","测试redis分片集群");
        System.out.println(sjedis.get("shards"));

    }

    @Test
    public void testCluster(){
        Set<HostAndPort> nodes =new HashSet<>();
        nodes.add(new HostAndPort("192.168.126.166",7000));
        nodes.add(new HostAndPort("192.168.126.166",7001));
        nodes.add(new HostAndPort("192.168.126.166",7002));
        nodes.add(new HostAndPort("192.168.126.166",7003));
        nodes.add(new HostAndPort("192.168.126.166",7004));
        nodes.add(new HostAndPort("192.168.126.166",7005));
        JedisCluster jedisCluster=new JedisCluster(nodes);
        jedisCluster.set("redisCluster","redis集群测试");
        System.out.println(jedisCluster.get("redisCluster"));
    }
}
