package io.github.akkhadka.webstore.controller.viewmodels.modelMapper;

import io.github.akkhadka.webstore.controller.viewmodels.UserViewModel;
import io.github.akkhadka.webstore.model.User;

public class UserMapper {
    public static User ToModel(UserViewModel vm){
        User user = new User(vm.getUsername(),vm.getPassword(),vm.getFirstName(),vm.getLastName(),vm.getEmail());
        return user;
    }
    public static UserViewModel ToView(User user){
        UserViewModel userVw = new UserViewModel();
        userVw.setFirstName(user.getFirstName());
        userVw.setLastName(user.getLastName());
        userVw.setPassword(user.getPassword());
        userVw.setConfirmPassword(user.getPassword());
        userVw.setEmail(user.getEmail());
        userVw.setUsername(user.getUsername());
        return userVw;
    }
}
