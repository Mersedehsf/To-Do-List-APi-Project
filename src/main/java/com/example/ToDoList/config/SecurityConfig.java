package com.example.ToDoList.config;


import com.example.ToDoList.config.jwt.CustomAccessDeniedHandler;
import com.example.ToDoList.config.jwt.JwtAuthenticationEntryPoint;
import com.example.ToDoList.config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    CustomAccessDeniedHandler accessDeniedHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/get/**").hasAnyRole("ADMIN")
                .requestMatchers("/user/update/**").hasAnyRole("ADMIN","USER")
                .requestMatchers("/user/physicalDelete/**").hasAnyRole("ADMIN","USER")
                .requestMatchers("/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
