package com.example.DoItBackendProject.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(h -> h
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin",
                                "http://localhost:3000"))
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods",
                                "POST, PUT, GET, OPTIONS, DELETE"))
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true")))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/perform_login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/users").permitAll()
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("http://localhost:3000/")
                        .loginProcessingUrl("/perform_login")
                        .usernameParameter("name")
                        .passwordParameter("password")
                        .defaultSuccessUrl("http://localhost:8080/admin/users")
                        .successHandler(new MySuccessLoginHandler()));

        return http.build();
    }
}
