package com.xsjt.core;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Collections;
import java.util.List;

public class LimitTests {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(2.0);
        for(int i = 0 ; i< 10 ;i ++) {
            double acq = rateLimiter.acquire();
            System.out.println(i + "获取令牌成功：" + acq);
        }

        List list = Collections.singletonList(111);
        System.out.println(list);
    }
}
