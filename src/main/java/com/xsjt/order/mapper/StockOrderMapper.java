package com.xsjt.order.mapper;

import org.springframework.stereotype.Repository;
import com.xsjt.order.model.StockOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * ClassName: StockOrderMapper
 *
 * @deprecated: 商品表 Mapper接口
 * @author: Joe
 * @date: 2018-07-13
 */
@Repository
public interface StockOrderMapper extends BaseMapper<StockOrder> {

    /**
     * 增加库存，并且返回增加的id
     * @param order
     * @return
     */
    int insertOrder(StockOrder order);
}
