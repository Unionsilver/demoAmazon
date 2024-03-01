package com.example.demo.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class CustomMacProvider {

    public static SecretKey generateKey() {
        try {
            // Генерация секретного ключа для использования в алгоритме HMAC
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
