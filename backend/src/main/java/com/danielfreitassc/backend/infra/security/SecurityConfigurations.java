package com.danielfreitassc.backend.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {
    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                .requestMatchers(HttpMethod.POST,"/user").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/user").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/user/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,"/user/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/user/{id}").hasRole("ADMIN")
                
                
                .requestMatchers(HttpMethod.POST,"/discipline").permitAll()
                .requestMatchers(HttpMethod.GET,"/discipline").permitAll()
                .requestMatchers(HttpMethod.GET,"/discipline/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT,"/discipline/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/discipline/{id}").permitAll()
                
                
                .requestMatchers(HttpMethod.POST,"/course").permitAll()
                .requestMatchers(HttpMethod.GET,"/course").permitAll()
                .requestMatchers(HttpMethod.GET,"/course/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT,"/course/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/course/{id}").permitAll()
                .requestMatchers(HttpMethod.PATCH,"/course/available/{id}").permitAll()
                
                
                .requestMatchers(HttpMethod.POST,"/lab").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/lab").hasAnyRole("ADMIN","PROFESSOR")
                .requestMatchers(HttpMethod.GET,"/lab/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/lab/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/lab/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/lab/list").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/lab/name/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,"/lab/available/{id}").hasRole("ADMIN")
                
                  
                .requestMatchers(HttpMethod.POST,"/lab-reserve").permitAll()
                .requestMatchers(HttpMethod.GET,"/lab-reserve").permitAll()
                .requestMatchers(HttpMethod.GET,"/lab-reserve/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT,"/lab-reserve/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/lab-reserve/{id}").permitAll()

                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/validation").hasRole("ADMIN")
                
                // Configuração para endpoint de erro
                .requestMatchers("/error").anonymous()
                .anyRequest().authenticated()

                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}