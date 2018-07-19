package com.xsjt.core.distributed.aop;

import com.xsjt.core.distributed.limit.RedisLimit;
import com.xsjt.core.exception.LimitExcetion;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 限流切面
 */
@Aspect
@Component
public class LimitAspect {

    private static Logger logger = LoggerFactory.getLogger(LimitAspect.class);

    @Qualifier("build")
    @Resource
    private RedisLimit redisLimit;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.xsjt.core.distributed.annotation.ControllerLimit)")
    public void pointCut() {}

    /**
     * 前置
     */
    @Before("pointCut()")
    public void before() {
        boolean limit = redisLimit.limit();
        if(!limit) {
            logger.warn("request has been limited");
            throw new LimitExcetion("request has been limited");
        }
    }
}
