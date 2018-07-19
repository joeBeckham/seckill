package com.xsjt.util.rate;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

/**
 * guava 令牌桶 限流
 */
@Component
public class AccessLimitService {

    /**
     *  设置QPS为10，即 每秒允许10个请求
     */
    RateLimiter rateLimiter = RateLimiter.create(10);

    /**
     * 尝试获取令牌
     * @return
     */
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}
