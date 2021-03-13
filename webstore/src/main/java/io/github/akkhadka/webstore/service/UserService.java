package io.github.akkhadka.webstore.service;

import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.service.customexception.UserAlreadyExistsException;
import io.github.akkhadka.webstore.service.customexception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User findbyUserName(String id);

    void signUp(User user) throws UserAlreadyExistsException;

    void updateProfile(User user) throws UserNotFoundException;

    List<User> getUsers();
}
