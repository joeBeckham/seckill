/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2018, xsjt- All Rights Reserved.
 * Project Name:xsjt-manager  
 * File Name:RetCode.java  
 * Package Name:com.xsjt.core.ret
 * Author   Joe
 * Date:2018年6月5日下午10:37:23
 * ---------------------------------------------------------------------------  
*/

package com.xsjt.core.ret;

/**
 * ClassName:RetCode 
 * 响应码枚举，参考HTTP状态码的语义
 * Date: 2018年6月5日 下午10:37:23
 * 
 * @author Joe
 * @version
 * @since JDK 1.8
 */
public enum RetCode {

	// 成功
	SUCCESS(200, "成功"),

	// 失败
	FAIL(400, "失败"),

	// 未认证（签名错误）
	UNAUTHORIZED(401 ,"未认证"),
	
	 /** 未登录 */
    UNAUTHEN(4401, "未登录"),

    /** 未授权，拒绝访问 */
    UNAUTHZ(4403 ,"未授权，拒绝访问"),

	// 接口不存在
	NOT_FOUND(404 ,"未找到"),
	
	// 不支持该请求方式
	REQUEST_METHOD_NOT_SUPPORTED(405 ,"不支持该请求方式"),

	// 限流
	CURRENT_LIMITING(100001, "请求限流"),

	// 服务器内部错误
	SERVER_ERROR(500, "服务器内部错误");

	public int code;
	private String msg;

	RetCode(int code) {
		this.code = code;
	}

	RetCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
