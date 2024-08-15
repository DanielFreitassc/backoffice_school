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

                .requestMatchers(HttpMethod.POST,"/user").permitAll()
                .requestMatchers(HttpMethod.GET,"/user").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/user/{username}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,"/user/{username}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/user/{username}").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST,"/lab").permitAll()
                .requestMatchers(HttpMethod.GET,"/lab").permitAll()
                .requestMatchers(HttpMethod.GET,"/lab/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/lab/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT,"/lab/{id}").permitAll()
                .requestMatchers(HttpMethod.GET,"/lab/list").permitAll()
                .requestMatchers(HttpMethod.GET,"/lab/name/{id}").permitAll()
                

                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                
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