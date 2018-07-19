package com.xsjt.core.handle;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xsjt.core.exception.LimitExcetion;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.ret.RetResult;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * ClassName: ExceptionHandle
 * 统一异常处理
 * date: 2018年6月7日 上午9:30:32
 * @author xxx  
 * @version   
 * @since JDK 1.8
 */
@ControllerAdvice
public class ExceptionHandle  extends WebMvcConfigurationSupport {


    /**
     * 统一异常处理方法
     * 抛出异常的方法抛出异常{
     *      Execption.class
     *      ServiceException.class
     * }
     * 通过{
     *      new RetResult<T>().setCode(serviceException.getCode()).setMsg(serviceException.getMessage());
     *      new RetResult<T>().setCode(RetCode.SERVER_ERROR).setMsg(e.getMessage());
     * }
     * 将异常信息由统一的响应消息格式返回
     *
     * @param e 异常信息
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public<T> RetResult<T> handle(HttpServletRequest req, Object handler, Exception e){
        Log log = LogFactory.get();
        RetResult<T> result = new RetResult<>();
        String msg;

        /**
         * 自定义异常
         */
        if (e instanceof ServiceException){
        	ServiceException serviceException = (ServiceException) e;
            result.setCode(serviceException.getCode()).setMsg(serviceException.getMessage());
            log.info(serviceException);
            return result;
        }

        /**
         * 404,访问的资源不存在
         */
        if (e instanceof NoHandlerFoundException){
            msg = "接口 [".concat(req.getRequestURI()).concat("] 不存在");
            result.setCode(RetCode.NOT_FOUND.getCode()).setMsg(msg).setData(null);
            log.warn(msg);
            return result;
        }
        
        /**
         * 405,不支持该请求方式
         */
        if (e instanceof HttpRequestMethodNotSupportedException){
            result.setCode(RetCode.REQUEST_METHOD_NOT_SUPPORTED.getCode()).setMsg(RetCode.REQUEST_METHOD_NOT_SUPPORTED.getMsg()).setData(null);
            log.warn(RetCode.REQUEST_METHOD_NOT_SUPPORTED.getMsg());
            return result;
        }

        /**
         * 请求限流
         */
        if(e instanceof LimitExcetion) {
            result.setCode(RetCode.CURRENT_LIMITING.getCode()).setMsg(RetCode.CURRENT_LIMITING.getMsg()).setData(null);
            log.warn(RetCode.CURRENT_LIMITING.getMsg());
            return result;
        }

        /**
         * 方法异常
         */
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            msg = String.format("接口 [%s] 出现异常,方法:%s.%s,异常摘要:%s",req.getRequestURL(),
                    method.getBean().getClass().getName(), method.getMethod().getName(), e.getMessage());
            result.setCode(RetCode.FAIL.getCode());
        }else { /** 其它所有异常 */
            msg = e.getMessage();
            result.setCode(RetCode.SERVER_ERROR.getCode());
        }

        log.error(e);
        return result.setMsg(msg).setData(null);
    }
    
    @Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new HandlerExceptionResolver() {
			@Override
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
				RetResult<Object> result = new RetResult<Object>();
				// 业务失败的异常，如“账号或密码错误”
				if (e instanceof ServiceException) {
					result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
					LogFactory.get().info(e.getMessage());
				} else if (e instanceof NoHandlerFoundException) {
					result.setCode(RetCode.NOT_FOUND).setMsg("接口 [" + request.getRequestURI() + "] 不存在");
				} else if (e instanceof ServletException) {
					result.setCode(RetCode.FAIL).setMsg(e.getMessage());
				}  else if (e instanceof LimitExcetion) {
                    result.setCode(RetCode.CURRENT_LIMITING).setMsg(e.getMessage());
                } else {
					result.setCode(RetCode.SERVER_ERROR).setMsg("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
					String message;
					if (handler instanceof HandlerMethod) {
						HandlerMethod handlerMethod = (HandlerMethod) handler;
						message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(), handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod()
								.getName(), e.getMessage());
					} else {
						message = e.getMessage();
					}
					LogFactory.get().error(message, e);
				}
				responseResult(response, result);
				return new ModelAndView();
			}
		});
	}
    
    private void responseResult(HttpServletResponse response, RetResult<Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
        	LogFactory.get().error(ex.getMessage());
        }
    }
}
