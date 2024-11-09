package com.bdool.chatbotservice.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해
                .allowedOrigins("http://localhost:3000") // 프론트엔드 도메인 허용 (React는 일반적으로 3000번 포트를 사용)
                .allowedOrigins("https://www.bdool.online")
                .allowedOrigins("https://bdool.online")
                .allowedOrigins("https://b-dool-front.vercel.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true) // 자격 증명(쿠키 등) 허용
                .maxAge(3600); // 캐시 기간 설정
    }
}
