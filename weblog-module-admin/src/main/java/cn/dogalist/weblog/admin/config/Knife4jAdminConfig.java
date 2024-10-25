package cn.dogalist.weblog.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Description: Knife4j API文档配置
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class Knife4jAdminConfig {
    @Bean("adminApi")
    public Docket createAdminApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2) // 指定生成文档的类型
                .groupName("Admin 后台接口")
                .apiInfo(buildApiInfo()) // 指定文档信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.dogalist.weblog.admin.controller")) // 指定扫描的包
                .paths(PathSelectors.any()) // 指定扫描的路径为所有
                .build();
    }

    /**
     * 构建文档信息
     *
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Weblog 后台接口文档")
                .description("Weblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")
                .termsOfServiceUrl("https://dogalist.cn") // 服务条款URL
                .contact(new springfox.documentation.service.Contact("monthwolf", "https://dogalist.cn", "1369755540@qq.com")) // 联系人信息
                .version("1.0")
                .build();
    }

}
