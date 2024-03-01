package com.example.demo.security;

import com.example.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Фильтр, который выполняется один раз за запрос для проверки JWT токенов
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private final JwtUtil jwtUtil; // Утилита для работы с JWT
    private final UserDetailsService userDetailsService; // Служба для загрузки информации о пользователе
    private final String jwtSecret; // Секретный ключ для подписи JWT

    // Внедрение зависимостей через конструктор
    @Autowired
    public JwtTokenFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService,
                          @Value("${jwt.secret}") String jwtSecret) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.jwtSecret = jwtSecret;
    }

    // Метод фильтрации для проверки JWT в заголовке Authorization каждого запроса
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Получение заголовка Authorization
        final String authHeader = request.getHeader("Authorization");

        // Проверка наличия JWT
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Извлечение токена из заголовка
            try {
                String username = jwtUtil.extractUsername(jwt); // Извлечение имени пользователя из токена

                // Проверка наличия имени пользователя и отсутствия аутентификации в контексте безопасности
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // Загрузка UserDetails

                    SecretKey secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HMACSHA256"); // Создание ключа для проверки подписи JWT

                    // Проверка валидности токена
                    if (jwtUtil.validateToken(jwt, username, secretKey)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()); // Создание аутентификационного токена
                        SecurityContextHolder.getContext().setAuthentication(authentication); // Установка аутентификации в контекст
                        logger.info("Security context was updated with authentication for '{}'", username);
                    }
                }
            } catch (Exception e) {
                logger.error("JWT Token validation error: {}", e.getMessage()); // Логирование ошибок валидации токена
            }
        }

        filterChain.doFilter(request, response); // Продолжение цепочки фильтров
    }
}
