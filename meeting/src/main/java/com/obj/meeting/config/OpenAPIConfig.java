package com.obj.meeting.config;

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
                .packagesToScan("com.obj.meeting")
                .build();
    }
}