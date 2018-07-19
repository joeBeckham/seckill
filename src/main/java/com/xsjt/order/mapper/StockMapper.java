package com.xsjt.order.mapper;

import org.springframework.stereotype.Repository;
import com.xsjt.order.model.Stock;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * ClassName: StockMapper
 *
 * @deprecated:  Mapper接口
 * @author: Joe
 * @date: 2018-07-13
 */
@Repository
public interface StockMapper extends BaseMapper<Stock> {

    /**
     * 乐观锁更新
     * @param stock
     * @return
     */
    int updateStockByOptimistic(Stock stock);

}
