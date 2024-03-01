package com.example.demo.controller;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.model.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Обозначает класс как контроллер, готовый обрабатывать web-запросы.
@RequestMapping("/api") // Устанавливает базовый путь для всех методов контроллера.
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class); // Логгер для записи информации о событиях в системе.

    private final AuthenticationManager authenticationManager; // Менеджер аутентификации для Spring Security.
    private final CustomUserDetailsService userDetailsService; // Сервис для загрузки данных пользователя.
    private final JwtUtil jwtUtil; // Утилита для работы с JWT.
    private final UserService userService; // Сервис для работы с пользователями.

    @Autowired // Автоматическое внедрение зависимостей через конструктор.
    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/public/authenticate") // Метод обрабатывает POST запросы на "/api/public/authenticate".
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        logger.info("Authentication request for user: {}", authenticationRequest.getUsername()); // Логирование запроса аутентификации.
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            ); // Попытка аутентификации пользователя.
            SecurityContextHolder.getContext().setAuthentication(authenticate); // Установка аутентификации в контексте безопасности.
            logger.info("Authentication successful for user: {}", authenticationRequest.getUsername()); // Логирование успешной аутентификации.
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}. Error: {}", authenticationRequest.getUsername(), e.getMessage()); // Логирование неудачной попытки аутентификации.
            return ResponseEntity.badRequest().body("Incorrect username or password"); // Возврат ответа с ошибкой.
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername()); // Загрузка данных пользователя.
        final String jwt = jwtUtil.generateToken(userDetails.getUsername()); // Генерация JWT.
        logger.info("JWT token generated for user: {}", authenticationRequest.getUsername()); // Логирование создания токена.

        return ResponseEntity.ok(new AuthenticationResponse(jwt)); // Возврат JWT в ответе.
    }

    @PostMapping("/public/register") // Метод обрабатывает POST запросы на "/api/public/register".
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerNewUser(user); // Регистрация нового пользователя.
            final UserDetails userDetails = userDetailsService.loadUserByUsername(registeredUser.getUsername()); // Загрузка данных пользователя.
            final String jwt = jwtUtil.generateToken(userDetails.getUsername()); // Генерация JWT для нового пользователя.
            logger.info("Registered and authenticated user: {}", registeredUser.getUsername()); // Логирование регистрации пользователя.
            return ResponseEntity.ok(new AuthenticationResponse(jwt)); // Возврат JWT в ответе.
        } catch (Exception e) {
            logger.error("Registration failed for user: {}. Error: {}", user.getUsername(), e.getMessage()); // Логирование ошибки регистрации.
            return ResponseEntity.badRequest().body("Registration error: " + e.getMessage()); // Возврат ответа с ошибкой.
        }
    }
}
