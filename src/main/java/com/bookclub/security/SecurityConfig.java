package com.bookclub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // Password encoder bean
        @Bean
        public PasswordEncoder passwordEncoder() {
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        // In-memory users
        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder encoder) {
                InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

                manager.createUser(User
                                .withUsername("user")
                                .password(encoder.encode("password"))
                                .roles("USER")
                                .build());

                manager.createUser(User
                                .withUsername("testuser01")
                                .password(encoder.encode("password01"))
                                .roles("USER", "ADMIN")
                                .build());

                return manager;
        }

        // Security filter chain for login, logout, and endpoint security
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                // Public resources
                                                .requestMatchers("/login", "/css/**", "/js/**", "/images/**")
                                                .permitAll()

                                                // Admin-only pages
                                                .requestMatchers("/monthly-books", "/monthly-books/list",
                                                                "/monthly-books/new")
                                                .hasRole("ADMIN")

                                                // All other requests require authentication
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutSuccessUrl("/login?logout=true")
                                                .invalidateHttpSession(true)
                                                .permitAll());

                return http.build();
        }
}
