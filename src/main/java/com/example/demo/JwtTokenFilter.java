package com.example.demo;

import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Логика извлечения и проверки JWT из запроса
        // Например, проверка наличия токена, его валидности и т.д.

        filterChain.doFilter(request, response); // Продолжаем цепочку фильтров
    }
}

