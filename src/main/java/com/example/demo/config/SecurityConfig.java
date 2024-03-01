package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Определяет класс как источник конфигурации бинов для контекста приложения.
@EnableWebSecurity // Включает поддержку безопасности веб-сервисов Spring Security.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // Объявляет бин для кодировщика паролей, использующий BCrypt хеш-функцию.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean // Переопределяет бин для менеджера аутентификации, чтобы его можно было автоматически внедрять в другие части приложения.
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and() // Позволяет запросам из разных источников обращаться к приложению.
                .csrf().disable() // Отключает межсайтовую подделку запроса (CSRF) защиту, что упрощает разработку.
                .authorizeRequests() // Начинает определение того, какие URL требуют аутентификации.
                .antMatchers("/api/public/**").permitAll() // Разрешает полный доступ к путям, соответствующим шаблону "/api/public/**".
                .anyRequest().authenticated() // Требует аутентификации для любых других запросов.
                .and()
                .sessionManagement() // Настраивает управление сессией.
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Приложение не будет использовать сессии для хранения состояния пользователя.
    }
}
