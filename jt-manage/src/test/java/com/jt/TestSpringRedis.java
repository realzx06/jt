package com.jt;

import cn.jt.SpringBootRun;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest(classes = SpringBootRun.class)
public class TestSpringRedis {

    @Autowired
    private Jedis jedis;

    @Test
    public void testRedis(){
        System.out.println(11111111);
        jedis.set("redis","测试SpringRedis");
        System.out.println(  jedis.get("redis"));
    }
}
