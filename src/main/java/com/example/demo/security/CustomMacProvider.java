package com.example.demo.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

// Класс для генерации секретного ключа, используемого в алгоритме HMAC
public class CustomMacProvider {

    // Метод для генерации секретного ключа с использованием алгоритма HmacSHA256
    public static SecretKey generateKey() {
        try {
            // Создание генератора ключей для алгоритма HmacSHA256
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            // Генерация секретного ключа
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            // Вывод в консоль информации об ошибке, если алгоритм не поддерживается
            e.printStackTrace();
            // Возвращение null, если ключ не может быть сгенерирован
            return null;
        }
    }
}
