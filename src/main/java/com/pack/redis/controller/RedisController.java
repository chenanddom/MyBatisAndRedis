package com.pack.redis.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController {
    /**
     * 添加普通键值对
     * @return
     */
@RequestMapping("/addSomeData")
public String cacheData(){
    String value="";
    Jedis jedis=new Jedis("192.168.28.130",6379);
    jedis.select(2);
    if (StringUtils.isEmpty(jedis.get("key_02"))) {
        jedis.set("key_02", "value_02");
        value = jedis.get("key_02");
        System.out.println("---重新设置---");
    }else {
     System.out.println("-----已经存在----");
        value = jedis.get("key_02");
    }
    return value;
}

    /**
     * 存取Map数据
     * @return
     */
    @RequestMapping("/addMapData")
    public String cacheMapData() {
        Jedis jedis = new Jedis("192.168.28.130", 6379);
        jedis.select(2);
        jedis.hset("group1", "key_04", "value_04");
        jedis.hset("group1", "key_05", "value_05");
        System.out.println("---------" + jedis.hget("group1", "key_04")+"---------");
        Map<String,String> map = new HashMap<String, String>();
        map.put("key_06","value_06");
        map.put("key_07","value_07");
        jedis.hmset("key_08",map);
        System.out.println("---------" + jedis.hmget("key_08","key_06","key_07")+"---------");
        return null;
    }
    /**
     *
     */



}
