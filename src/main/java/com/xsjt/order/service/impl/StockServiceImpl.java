package com.xsjt.order.service.impl;

import com.xsjt.order.model.Stock;
import com.xsjt.order.mapper.StockMapper;
import com.xsjt.order.service.IStockService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: StockServiceImpl
 *
 * @deprecated:  库存表 服务实现类
 * @author: Joe
 * @date: 2018-07-13
 */
@Service(value = "stockService")
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Autowired
    private StockMapper stockMapper;
}
