package com.xsjt.order.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * ClassName: StockOrder
 *
 * @deprecated: 订单表
 * @author: Joe
 * @date: 2018-07-13
 */
@TableName("stock_order")
@ApiModel("订单表")
public class StockOrder extends Model<StockOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 库存ID
     */
    @ApiModelProperty("库存ID")
    private Integer sid;
    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String name;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StockOrder{" +
        "id=" + id +
        ", sid=" + sid +
        ", name=" + name +
        ", createTime=" + createTime +
        "}";
    }
}
