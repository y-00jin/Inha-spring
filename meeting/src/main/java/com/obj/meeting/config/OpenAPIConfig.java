package com.obj.meeting.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PackageName : com.obj.meeting.config
 * @FileName : OpenAPIConfig
 * @Date : 9/25/24
 * @Author : y00jin
 * @Description :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/25/24        y00jin       최초 생성
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public GroupedOpenApi publicAPI(){
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.obj.meeting2")
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Group Meeting")
                .version("1.0")
                .description("This is Group Meeting Service API"));
    }
}