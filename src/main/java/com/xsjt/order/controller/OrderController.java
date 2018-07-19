package com.xsjt.order.controller;

import com.xsjt.core.common.BaseController;
import com.xsjt.core.distributed.annotation.ControllerLimit;
import com.xsjt.order.service.IStockOrderService;
import com.xsjt.util.rate.AccessLimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 下单控制层
 * @author: Joe
 * @date: 2018-07-13
 */
@Api(value = "订单", description = "订单接口")
@RestController
@RequestMapping("/")
public class OrderController extends BaseController {

    @Autowired
    private IStockOrderService stockOrderService;
    @Autowired
    private AccessLimitService limitService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     *  应用限流  下单
     * @param sid
     * @return
     */
    @ApiOperation(value="下单", notes="根据商品id下单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "商品ID", required = true,
                    dataType = "int",  paramType = "path")
    })
    @PostMapping("/createOrder/{sid}")
    public Object createOrder(@PathVariable int sid) {
        int id = 0;
        if(limitService.tryAcquire()) {
            logger.info(Thread.currentThread().getName() + " 请求 success");
            try {
                id = stockOrderService.createOrder(sid);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            if(id != 0) {
                return ok("新增成功", id);
            }
            return faln("新增失败");
        } else {
            logger.info(Thread.currentThread().getName() + " 请求 limit");
            return faln("获取令牌失败！");
        }
    }

    /**
     * 分布式限流
     * @param sid
     * @return
     */
    @ApiOperation(value="下单", notes="根据商品id下单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "商品ID", required = true,
                    dataType = "int",  paramType = "path")
    })
    @PostMapping("/order/{sid}")
    @ControllerLimit
    public Object order(@PathVariable int sid) {
        int id = 0;
        logger.info(Thread.currentThread().getName() + " 请求 success");
        try {
            id = stockOrderService.createOrder(sid);
        } catch (Exception e) {
            logger.error("创建订单异常：" + e.getMessage(), e);
        }
        if(id != 0) {
            return ok("新增成功", id);
        }
        return faln("新增失败");
    }

}
