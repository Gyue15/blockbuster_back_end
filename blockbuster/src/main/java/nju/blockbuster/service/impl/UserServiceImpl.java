package nju.blockbuster.service.impl;

import nju.blockbuster.entities.User;
import nju.blockbuster.models.UserModel;
import nju.blockbuster.repository.UserRepository;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserModel findUser(String email) {
        UserModel userModel = new UserModel();
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            return userModel;
        }
        userModel.setAvatar(user.getAvatar());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setUsername(user.getUsername());
        return userModel;
    }

}
