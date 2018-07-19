package com.xsjt.order.service;

import com.xsjt.order.model.StockOrder;
import com.baomidou.mybatisplus.service.IService;

/**
 * ClassName: IStockOrderService
 *
 * @deprecated: 订单表 服务类
 * @author: Joe
 * @date: 2018-07-13
 */
public interface IStockOrderService extends IService<StockOrder> {

    int createOrder(int sid) throws Exception;
}
