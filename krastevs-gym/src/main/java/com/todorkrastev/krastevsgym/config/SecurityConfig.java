package com.todorkrastev.krastevsgym.config;

import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.impl.KrastevsGymUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/", "/users/login", "/users/register", "/error", "/api/convert").permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(
                        formLogin ->
                                formLogin
                                        .loginPage("/users/login")
                                        .usernameParameter("email")
                                        .passwordParameter("password")
                                        .defaultSuccessUrl("/", true)
                                        .failureForwardUrl("/users/login-error")
                )
                .logout(
                        logout ->
                                logout
                                        .logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true)
                )
                .build();
    }

    @Bean
    public KrastevsGymUserDetailsService userDetailsService(UserRepository userRepository) {
        return new KrastevsGymUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
