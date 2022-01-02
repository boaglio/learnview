package com.learnview.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // @Bean
    // public Docket api() {
    // return new Docket(DocumentationType.OAS_30).select()
    // .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
    // .build();
    // }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("learnview-public").pathsToMatch("/api/**").build();
    }

}
