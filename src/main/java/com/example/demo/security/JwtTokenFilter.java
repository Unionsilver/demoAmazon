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
import java.util.Base64;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final String jwtSecret;

    @Autowired
    public JwtTokenFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService,
                          @Value("${jwt.secret}") String jwtSecret) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                String username = jwtUtil.extractUsername(jwt);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    SecretKey secretKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), "HMACSHA256");

                    if (jwtUtil.validateToken(jwt, username, secretKey)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        logger.info("Security context was updated with authentication for '{}'", username);
                    }
                }
            } catch (Exception e) {
                logger.error("JWT Token validation error: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
