package com.kpi.fellowtravelersfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("driver")
                .password("driver")
                .roles("DRIVER")
                .and()
                .withUser("user")
                .password("user")
                .roles("USER");
    }
    @Bean
    public AuthenticationSuccessHandler authenticationHandler() {
        return new AuthenticationHandler();
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/driver/**", "/trips/**").hasRole("DRIVER")
                .antMatchers("/driver/**", "/trips/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/user/**", "/trips/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/user/**", "/trips/**").permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login").successHandler(authenticationHandler())
                .and()
                .headers()
                .xssProtection();
    }
}
