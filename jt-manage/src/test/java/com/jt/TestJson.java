package com.jt;


import cn.jt.pojo.Item;
import cn.jt.pojo.ItemDesc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Date;

public class TestJson {

    private static final ObjectMapper MAPPER =new ObjectMapper();

    /*
        测试对象与Json的转化
     */
    @Test
    public void testJsonObject() throws JsonProcessingException {
        ItemDesc id=new ItemDesc();
        id.setItemId(101L);
        id.setItemDesc("测试Json");
        id.setCreated(new Date());
        id.setUpdated(id.getCreated());
        //对象转JSON
        String s = MAPPER.writeValueAsString(id);
        System.out.println(s);
        //JSON转对象
        ItemDesc itemDesc = MAPPER.readValue(s, ItemDesc.class);
        System.out.println(itemDesc.getItemId());

    }
}
