/**
 * ---------------------------------------------------------------------------
 * Copyright (c) 2018, company- All Rights Reserved.
 * Project Name:xsjt-manager
 * File Name:RedisConfiguration.java
 * Package Name:com.xsjt.core.configurer
 * Author   xxx
 * Date:2018年6月14日下午3:50:40
 * ---------------------------------------------------------------------------
*/
package com.xsjt.core.config;
import com.xsjt.core.distributed.enums.RedisTypeEnum;
import com.xsjt.core.distributed.limit.RedisLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import redis.clients.jedis.JedisPoolConfig;

/**
 * ClassName:RedisConfig
 * Redis的配置类
 * Date:     2018年6月14日 下午3:50:40
 * @author   xxx
 * @version
 * @since    JDK 1.8
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${redis.limit}")
    private int limit;

    @Autowired
    private JedisConfig redisConfig;

    public JedisConnectionFactory convertJedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisConfig.getHost());
        jedisConnectionFactory.setPort(redisConfig.getPort());
        jedisConnectionFactory.setPassword(redisConfig.getPassword());

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfig.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        jedisPoolConfig.setTestOnBorrow(redisConfig.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(redisConfig.isTestOnReturn());
        jedisPoolConfig.setTestWhileIdle(redisConfig.isTestWhileIdle());

        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }


    /**
     * 读取 lua 脚本，实例化 限流工具类
     * @return
     */
    @Bean("redisLimit")
    public RedisLimit build() {
        // 读取 lua 脚本
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("ratelimit.lua"));
        redisScript.setResultType(java.lang.Long.class);

        RedisLimit redisLimit = new RedisLimit.Builder(this.convertJedisConnectionFactory(), redisScript, RedisTypeEnum.single.getCode())
                .limit(limit)
                .build();
        return redisLimit;
    }

}
