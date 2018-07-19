package com.xsjt.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * ClassName: SwaggerConfig
 * Swagger2配置文件
 * date: 2018年6月6日 下午7:35:22
 * @author xxx  
 * @version   
 * @since JDK 1.8
 */
@Configuration
public class SwaggerConfig {

    /**
     * 创建api文档.默认分组.
     * 创建多个bean实现创建多组文档.
     * @return
     */
    @Bean(value = "defaultApi")
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("默认分组")
                .select()
                //控制层所在的包
                .apis(RequestHandlerSelectors.basePackage("com.xsjt"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Xsjt-System")
                .description("xsjt 接口描述")
                .termsOfServiceUrl("https://github.com/joebeckham")
                .contact(new Contact("Joe", "http://joebeckham.github.io", "xbq8080@163.com"))
                .version("1.0")
                .build();
    }
}
