package com.xsjt.order.service.impl;

import com.xsjt.order.mapper.StockMapper;
import com.xsjt.order.model.Stock;
import com.xsjt.order.model.StockOrder;
import com.xsjt.order.mapper.StockOrderMapper;
import com.xsjt.order.service.IStockOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: StockOrderServiceImpl
 *
 * @deprecated: 订单表 服务实现类
 * @author: Joe
 * @date: 2018-07-13
 */
@Service(value = "stockOrderService")
public class StockOrderServiceImpl extends ServiceImpl<StockOrderMapper, StockOrder> implements IStockOrderService {

    @Autowired
    private StockOrderMapper stockOrderMapper;
    @Autowired
    private StockMapper stockMapper;

    /**
     * 创建订单逻辑
     * @param sid
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createOrder(int sid) throws Exception {
        // 检验库存
        Stock stock = checkStock(sid);
        // 库存减1
        this.saleStockByOptimistic(stock);
        // 增加订单
        int i = createOrder(stock);
        return i;
    }

    /**
     * 校验库存
     * @param sid
     * @return
     */
    private Stock checkStock(int sid) {
        Stock stock = stockMapper.selectById(sid);
        if(stock == null || stock != null && stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足");
        }
        return stock;
    }

    /**
     * 减库存
     * @param stock
     */
    private void saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        stockMapper.updateById(stock);
    }

    /**
     * 乐观锁减库存
     * @param stock
     */
    private void saleStockByOptimistic(Stock stock) {
        int count = stockMapper.updateStockByOptimistic(stock);
        if(count == 0) {
            throw  new RuntimeException("并发更新DB失败");
        }
    }

    /**
     * 增加订单
     * @param stock
     * @return
     */
    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        int i = stockOrderMapper.insertOrder(order);
        return i;
    }

}