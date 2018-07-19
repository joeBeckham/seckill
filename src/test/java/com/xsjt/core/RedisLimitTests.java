package com.xsjt.core;


import com.xsjt.core.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLimitTests {

    @Autowired
    private RedisConfig redisConfig;

    @Test
    public void test() {
        System.out.println("99999999999:" + redisConfig.build().limit());
    }
}
