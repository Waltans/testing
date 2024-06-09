package com.urfu.testing.application.services;

import com.urfu.testing.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    /**
     * Сохранение пользователя
     *
     * @param user - пользователь
     * @return User
     */
    User saveUser(User user);

    /**
     * Обновление пользователя
     *
     * @param id       - UUID
     * @param username - имя пользователя
     * @param password - пароль пользователя
     */
    void updateUser(UUID id, String username, String password);

    /**
     * Удаление пользователя по UUID
     *
     * @param uuid - UUID
     */
    void deleteUser(UUID uuid);

}
