package com.xsjt.core.distributed.limit;

import com.xsjt.core.distributed.enums.RedisTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import java.util.Collections;

/**
 * redis　限流工具类
 */
public class RedisLimit {

    private static Logger logger = LoggerFactory.getLogger(RedisLimit.class);

    private int limit = 5;
    private String type;
    private static final int FAIL_CODE = 0;

    private JedisConnectionFactory jedisConnectionFactory;
    private RedisScript redisScript;

    public RedisLimit(Builder builder) {
        this.limit = builder.limit;
        this.type = builder.type;
        this.jedisConnectionFactory = builder.jedisConnectionFactory;
        this.redisScript = builder.redisScript;
    }

    /**
     * 限流方法
     * @return
     */
    public boolean limit() {
        Object connection = this.getConnection();
        Object result = this.limitRequest(connection);
        if(FAIL_CODE != (Long) result) {
            return true;
        }
        return false;
    }

    /**
     * 执行lua脚本
     * @param connection
     * @return
     */
    private Object limitRequest(Object connection) {
        Object result = null;
        String key = String.valueOf(System.currentTimeMillis() / 1000);
        if(connection instanceof Jedis) {
            result = ((Jedis)connection).eval(redisScript.getScriptAsString(), Collections.singletonList(key),
                    Collections.singletonList(String.valueOf(limit)));
            ((Jedis)connection).close();
        } else if(connection instanceof JedisCluster){
            result = ((JedisCluster)connection).eval(redisScript.getScriptAsString(), Collections.singletonList(key),
                    Collections.singletonList(String.valueOf(limit)));
           try{
               ((JedisCluster)connection).close();
           } catch(Exception e) {
                logger.error(e.getMessage(), e);
           }
        }
        return result;
    }

    /**
     * 获取连接
     * @return
     */
    private Object getConnection() {
        Object connection = null;
        // 单节点
        if(RedisTypeEnum.single.getCode().equals(type)) {
            connection = jedisConnectionFactory.getConnection().getNativeConnection();
        } else {
            connection = jedisConnectionFactory.getClusterConnection().getNativeConnection();
        }
        return connection;
    }

    public static class Builder {
        private int limit = 5;
        private String type ;
        private JedisConnectionFactory jedisConnectionFactory;
        private DefaultRedisScript redisScript;

        public Builder(JedisConnectionFactory jedisConnectionFactory, DefaultRedisScript redisScript, String type) {
            this.jedisConnectionFactory = jedisConnectionFactory;
            this.type = type;
            this.redisScript = redisScript;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

       public RedisLimit build() {
            return new RedisLimit(this);
       }
    }

}

