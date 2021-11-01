package com.example.server.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengyinshan
 * @version 1.0.0
 * @createTime 2021/11/1 19:13
 * @Description Swagger2配置类
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定哪些包生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.example.server.controller"))
                // 该包下所有路径
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes((List<SecurityScheme>) securitySchemes());
    }








//    private Predicate<String> getPath(String regexPath) {
//        return PathSelectors.regex(regexPath);
//    }




    /**
     * 文档描述信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云E办接口文档")
                .description("云E办接口文档")
                .contact(new Contact("鄧鄧","http://localhost:8081/doc.html","2443857790@qq.com"))
                .version("1.0")
                .build();
    }

    /**
     * 设置请求头信息并返回对象
     * @return
     */
    private List<? extends SecurityScheme> securitySchemes() {
        List<ApiKey> result = new ArrayList<>();
        // apikey的名字，参数名字，参数所在位置
        result.add(new ApiKey("Authorization", "Authorization", "Header"));
        return result;
    }

    /**
     * 设置需要认证的路径
     * @return
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    /**
     * 获取上下文环境
     * @param pathRegex
     * @return
     */
    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                // 授权路径
                .forPaths(PathSelectors.regex(pathRegex))
                // 授权引用
                .securityReferences(defaultAuth())
                .build();
    }

    /**
     * 添加全局的Authorization
     * @return
     */
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        // 设置授权范围
        AuthorizationScope scope = new AuthorizationScope("global","access Everything");
        // SecurityReference()方法要求必须要添加数组，所以创建scopes数组
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = scope;
        result.add(new SecurityReference("Authorization",scopes));
        return result;
    }

}
