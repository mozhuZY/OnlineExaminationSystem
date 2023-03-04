package com.zy.oes.config.swagger;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @projectName: OnlineExaminationSystem
 * @className: SwaggerConfig
 * @author: MoZhu
 * @date: 2023/3/4 21:41
 * @description: <p> Swagger配置类 </p>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建一个Swagger配置Bean
     * @return bean
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("oesAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zy.oes.module"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 返回api信息
     * @return api信息
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "周瀛",
                "https://space.bilibili.com/28971299?spm_id_from=333.1007.0.0",
                "2237494913@qq.com"
        );
        return new ApiInfoBuilder()
                .title("OES API文档")
                .description("此页面为OnlineExaminationSystem系统接口测试页面")
                .version("1.0")
//                .license()
//                .licenseUrl()
                .contact(contact)
                .build();
    }
}
