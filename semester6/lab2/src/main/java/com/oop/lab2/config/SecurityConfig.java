package com.oop.lab2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Credentials"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
                .and()
                //.addFilterBefore(keycloakPreAuthActionsFilter(), LogoutFilter.class)
                //.addFilterBefore(keycloakAuthenticationProcessingFilter(), X509AuthenticationFilter.class)
                //.addFilterBefore(exceptionTranslationFilter(), KeycloakPreAuthActionsFilter.class)
                .authorizeRequests()

                .antMatchers(HttpMethod.OPTIONS, "*").permitAll()
                .antMatchers(HttpMethod.GET, "/projects").permitAll()
                .antMatchers(HttpMethod.GET, "/projects/**").permitAll()
                .antMatchers(HttpMethod.GET, "/projects/****").permitAll()
                .antMatchers(HttpMethod.PUT, "/projects/****").permitAll()
                .antMatchers(HttpMethod.GET, "/user/*").permitAll()
                .antMatchers(HttpMethod.GET, "/dev/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/dev/***").permitAll()
                .antMatchers(HttpMethod.GET, "/calc/*").permitAll()
        ;
        //.and()
        //.logout()
        //.addLogoutHandler(keycloakLogoutHandler())
        //.logoutUrl("/sso/logout").permitAll()
        //.logoutSuccessUrl("/");
    }*/
}