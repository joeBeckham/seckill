package com.xsjt.order.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * ClassName: Stock
 *
 * @deprecated: 
 * @author: Joe
 * @date: 2018-07-13
 */
@ApiModel("库存表")
public class Stock extends Model<Stock> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer count;
    /**
     * 已售
     */
    @ApiModelProperty("已售")
    private Integer sale;
    /**
     * 乐观锁，版本号
     */
    @ApiModelProperty("乐观锁，版本号")
    private Integer version;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Stock{" +
        "id=" + id +
        ", name=" + name +
        ", count=" + count +
        ", sale=" + sale +
        ", version=" + version +
        "}";
    }
}
