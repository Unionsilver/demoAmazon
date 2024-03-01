package com.example.demo.exception;

// Класс исключения, который используется, когда попытка зарегистрировать нового пользователя не удалась из-за существования пользователя с таким же именем
public class UserAlreadyExistsException extends RuntimeException {
    private String username; // Имя пользователя, вызвавшее исключение

    // Конструктор исключения, принимающий сообщение и имя пользователя
    public UserAlreadyExistsException(String message, String username) {
        super(message); // Вызов конструктора базового класса RuntimeException с сообщением об ошибке
        this.username = username; // Сохранение имени пользователя, вызвавшего исключение
    }

    // Геттер для получения имени пользователя, из-за которого было выброшено исключение
    public String getUsername() {
        return username;
    }

    // При необходимости здесь можно добавить другие поля или методы, которые могут быть полезны для более детальной обработки этого исключения
}
