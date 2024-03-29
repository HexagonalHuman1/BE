package com.example.huewith.configuration;

import com.example.huewith.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final MemberService memberService;

    @Value("${jwt.secret}")
    private String secretKey;  // final 제거

    // Lombok의 @RequiredArgsConstructor 대신 명시적 생성자 사용
    public SecurityConfig(MemberService memberService) { //애플리케이션이 시작될 때 자동으로 생성되고 적용됨
        this.memberService=memberService;
    }

    // 생성자 주입 방식을 사용 (Lombok의 @RequiredArgsConstructor 사용 가능)

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // CSRF 설정 비활성화
                .csrf((csrfConfig)->csrfConfig.disable())
                // 모든 요청에 대해 접근 허용
                .authorizeHttpRequests(config->config
                        .requestMatchers("/swagger-ui/**","/webjars/**",
                        "/v3/api-docs/**","/swagger-resources/**","/api/v1/member/**"
                        ).permitAll() // 로그인 및 회원가입 경로 허용
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                );
                // JWT필터를 통과해야 함
        http.
                addFilterBefore(new JwtFilter(memberService, secretKey), LogoutFilter.class)
                // CORS 설정
                .addFilter(corsFilter())
                // 세션 사용 안함 (JWT 사용을 위해)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        //config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

//    private CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);  //자격 증명 정보 허용
//        config.addAllowedOriginPattern("*"); //모든 출저에서 요청을 허용
//        config.addAllowedHeader("*"); //모든 헤더 허용
//        config.addAllowedMethod("*"); //모든 HTTP 메소드 허용
//        source.registerCorsConfiguration("/**", config);  //모든 URL패턴에 CORS설정 적용
//        return source;
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
