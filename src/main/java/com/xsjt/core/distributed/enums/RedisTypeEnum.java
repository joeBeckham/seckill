package com.xsjt.core.distributed.enums;

/**
 * 　redis 类型
 */
public enum RedisTypeEnum {

    single("1", "单节点"),
    cluster("2" , "集群");

    private String code;
    private String msg;

    RedisTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
