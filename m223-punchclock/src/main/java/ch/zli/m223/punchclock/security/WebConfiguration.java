package ch.zli.m223.punchclock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Also see https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-cors
 */
@Configuration
public class WebConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            //aktiviert das CORS-Mapping für die Wildcard "/**"
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
