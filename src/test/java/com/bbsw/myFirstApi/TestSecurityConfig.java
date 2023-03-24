package com.bbsw.myFirstApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile(value = "test")
public class TestSecurityConfig {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeHttpRequests().anyRequest().permitAll().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
    }
}