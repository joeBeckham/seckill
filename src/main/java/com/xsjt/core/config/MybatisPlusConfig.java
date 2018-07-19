package com.xsjt.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * MybatisPlusConfig
 * mybatis-plus配置文件
 * ClassName: MybatisPlusConfig
 * date: 2018年5月9日 下午4:53:17
 * @author Joe  
 * @version   
 * @since JDK 1.8
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.xsjt.*.mapper"}) //mybatis-plus扫描Mapper.java
public class MybatisPlusConfig {
	
	/**
	 * paginationInterceptor:( 分页插件,自动识别数据库类型).  
	 * @author xxx
	 * Date:2018年6月6日下午6:51:43
	 *
	 * @return
	 */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
