package com.security.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails normalUser = User.withUsername("Keshava")
                .password(passwordEncoder().encode("kes123"))
                .roles("NORMAL")
                .build();

        UserDetails adminUser = User.withUsername("Admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(
                normalUser, adminUser
        );

        return inMemoryUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers("/user/fetch")
                .hasRole("ADMIN")

                .requestMatchers("/user/normal/**")
                .hasRole("NORMAL")

                .requestMatchers("/user/greet")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }
}
