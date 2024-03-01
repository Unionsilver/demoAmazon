package com.example.demo.service;

import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

// Сервис пользователей, реализующий интерфейс UserDetailsService
@Primary
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Внедрение зависимостей через конструктор
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод регистрации нового пользователя
    public User registerNewUser(User user) {
        // Проверка наличия пользователя с таким же именем
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists", "Username: " + user.getUsername());
        }
        // Хеширование пароля перед сохранением пользователя
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Сохранение пользователя
        return userRepository.save(user);
    }

    // Метод загрузки пользователя по имени пользователя для аутентификации
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Поиск пользователя в репозитории по имени пользователя
        User user = userRepository.findByUsername(username);
        if (user == null) {
            // Если пользователь не найден, генерируется исключение
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Преобразование списка ролей пользователя в список объектов SimpleGrantedAuthority
        Collection<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // Создание объекта UserDetails на основе найденного пользователя
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
