package com.xsjt.core.distributed.annotation;

import java.lang.annotation.*;

/**
 *  控制层 注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLimit {

}
