package com.xsjt.core.page;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.mybatisplus.plugins.Page;
import com.xsjt.core.exception.ServiceException;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;

/**
 * ClassName: PageFactory
 * 分页参数.可在此创建多个分页方式.接收不同前台发送的分页参数
 * date: 2018年6月6日 下午5:39:06
 * @author xxx  
 * @version @param <T>  
 * @since JDK 1.8
 */
public class PageFactory<T> {

    public Page<T> defaultPage(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> params = ServletUtil.getParamMap(request);

        if (!params.containsKey("current") || !params.containsKey("size")){
            throw new ServiceException("缺少分页参数");
        }

        // 第几页
        Integer current = Integer.valueOf(params.get("current"));
        // 每页大小
        Integer size = Integer.valueOf(params.get("size"));
        // 排序字段
        String sort = params.get("sort");
        // asc或desc,升序或降序
        String order = params.get("order");

        if (StrUtil.isBlank(sort)){
            Page<T> page = new Page<>(current,size);
            page.setOpenSort(false);
            return page;
        }else {
            Page<T> page = new Page<>(current,size,sort);
            boolean asc = Order.ASC.getDes().equals(order);
            page.setAsc(asc);
            return page;
        }
    }
}
