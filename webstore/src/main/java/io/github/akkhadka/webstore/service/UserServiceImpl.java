package io.github.akkhadka.webstore.service;
import io.github.akkhadka.webstore.model.User;
import io.github.akkhadka.webstore.model.UserRepository;
import io.github.akkhadka.webstore.repository.FakeUserRepository;
import io.github.akkhadka.webstore.service.customexception.UserAlreadyExistsException;
import io.github.akkhadka.webstore.service.customexception.UserNotFoundException;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(){
        userRepository = new FakeUserRepository();
    }
    public User findbyUserName(String username) {
        User user = userRepository.find(username);
        return user;
    }

    @Override
    public void signUp(User login) throws UserAlreadyExistsException {
        if(userRepository.find(login.getUsername())!=null)
        {
           throw new UserAlreadyExistsException("User already exists.");
        }else {
            userRepository.save(login);
        }

    }

    @Override
    public void updateProfile(User user) throws UserNotFoundException {
        if(userRepository.find(user.getUsername())==null) {
            throw new UserNotFoundException("User not found to be updated");
        }
        userRepository.update(user);

    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
