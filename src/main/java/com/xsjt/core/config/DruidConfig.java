/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2018, xsjt- All Rights Reserved.
 * Project Name:xsjt-manager  
 * File Name:DruidMonitorConfigurer.java  
 * Package Name:com.xsjt.core
 * Author   Joe
 * Date:2018年6月5日下午9:20:15
 * ---------------------------------------------------------------------------  
*/

package com.xsjt.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * ClassName:DruidMonitorConfigurer 
 * Druid监控配置
 * Date: 2018年6月5日 下午9:20:15
 * @author Joe
 * @version
 * @since JDK 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidConfig {
	
    private String loginUsername;

    private String loginPassword;

    private String allow;

    private String deny;

    private String resetEnable;

	/**
	 * registrationBean:(注册ServletRegistrationBean).  
	 * @author Joe
	 * Date:2018年6月5日下午10:11:10
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> registrationBean() {
		ServletRegistrationBean<StatViewServlet> bean = 
				new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
		/** 初始化参数配置，initParams **/
		// 白名单多个ip逗号隔开
		bean.addInitParameter("allow", allow);
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		bean.addInitParameter("deny", deny);
		// 登录查看信息的账号密码.
		bean.addInitParameter("loginUsername", loginUsername);
		bean.addInitParameter("loginPassword", loginPassword);
		// 是否能够重置数据.
		bean.addInitParameter("resetEnable", "false");
		return bean;
	}
	
	/**
	 * druidStatFilter:(注册FilterRegistrationBean).  
	 * @author Joe
	 * Date:2018年6月5日下午10:11:31
	 *
	 * @return
	 */
    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = 
        		new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
        // 添加过滤规则.
        bean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getDeny() {
		return deny;
	}

	public void setDeny(String deny) {
		this.deny = deny;
	}

	public String getResetEnable() {
		return resetEnable;
	}

	public void setResetEnable(String resetEnable) {
		this.resetEnable = resetEnable;
	}
    
}
