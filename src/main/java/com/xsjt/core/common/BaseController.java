package com.xsjt.core.common;

import com.baomidou.mybatisplus.plugins.Page;
import com.xsjt.core.page.PageFactory;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;

/**
 * ClassName: BaseController
 * 控制层的基类
 * date: 2018年6月6日 下午5:45:30
 * @author xxx  
 * @version   
 * @since JDK 1.8
 */
public abstract class BaseController {

    /**
     * 成功的消息返回
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(){
        return new RetResult<T>().setCode(RetCode.SUCCESS.getCode()).setMsg(RetCode.SUCCESS.getMsg());
    }

    /**
     * 成功的消息返回
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(String msg){
        return new RetResult<T>().setCode(RetCode.SUCCESS.getCode()).setMsg(msg);
    }

    /**
     * 成功的消息返回
     * @param data 响应数据
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(T data){
        return new RetResult<T>().setCode(RetCode.SUCCESS.getCode()).setMsg(RetCode.SUCCESS.getMsg()).setData(data);
    }

    /**
     * 成功的消息返回
     * @param msg 响应消息
     * @param data 响应数据
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> ok(String msg, T data){
        return new RetResult<T>().setCode(RetCode.SUCCESS.getCode()).setMsg(msg).setData(data);
    }

    /**
     * 失败的消息返回
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> faln(String msg){
        return new RetResult<T>().setCode(RetCode.FAIL.getCode()).setMsg(msg);
    }

    /**
     * 失败的消息返回
     * @param code 自定义响应码
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> faln(int code, String msg){
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    /**
     * 发生错误时的消息返回
     * @param msg 响应消息
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> error(String msg){
        return new RetResult<T>().setCode(RetCode.SERVER_ERROR.getCode()).setMsg(msg);
    }

    /**
     * 未找到内容的消息返回
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> notFound(){
        return new RetResult<T>().setCode(RetCode.NOT_FOUND.getCode()).setMsg(RetCode.NOT_FOUND.getMsg());
    }

    /**
     * 未认证的消息返回
     * @param <T>
     * @return
     */
    protected <T> RetResult<T> unauthorized(){
        return new RetResult<T>().setCode(RetCode.UNAUTHORIZED.getCode()).setMsg(RetCode.UNAUTHORIZED.getMsg());
    }

    /**
     * 默认分页方式
     * @param <T>
     * @return
     */
    protected<T> Page<T> defaultPage(){
        return new PageFactory<T>().defaultPage();
    }


}
