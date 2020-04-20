package com.jt;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class TestSentinel {
    @Test
    public void testSentinel(){
        Set<String> sentinel= new HashSet();
        sentinel.add("192.168.126.166:26379");
        //获取连接哨兵对象
        JedisSentinelPool pool= new JedisSentinelPool("mymaster",sentinel);
        //获取Jedis
        Jedis jedis = pool.getResource();
        jedis.set("sentinel","测试连接哨兵");
        System.out.println( jedis.get("sentinel"));
    }
}
