/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2018, company- All Rights Reserved.
 * Project Name:xsjt-manager  
 * File Name:ServiceException.java  
 * Package Name:com.xsjt.core.ret
 * Author   xxx
 * Date:2018年6月6日下午3:57:02
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.core.exception;

import com.xsjt.core.ret.RetCode;
import java.io.Serializable;

/**  
 * ClassName:ServiceException 
 * 业务类异常
 * Date:     2018年6月6日 下午3:57:02
 * @author   xxx  
 * @version    
 * @since    JDK 1.8
 */
public class ServiceException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1213855733833039552L;
	
	private int code;

	public ServiceException() {
	}
	
	/**
     * 自定义异常01
     * @param retCode @see RetCode 类型的枚举信息
     */
    public ServiceException(RetCode retCode) {
        super(retCode.getMsg());
        this.code = retCode.getCode();
    }
    
    /**
     * 自定义异常02
     * @param msg 异常信息,错误码默认为400
     */
    public ServiceException(String msg) {
        super(msg);
        this.code = 400;
    }

    /**
     * 自定义异常03
     * @param code 错误码
     * @param msg 错误信息
     */
    public ServiceException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
