package nju.blockbuster.service.impl;

import nju.blockbuster.entities.User;
import nju.blockbuster.models.UserModel;
import nju.blockbuster.repository.UserRepository;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ResultMessage;

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

    @Override
    public ResultMessage addUser(UserModel userModel) {
        User user = new User();
        user.setAvatar(userModel.getAvatar());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setUsername(userModel.getUsername());
        user = userRepository.saveAndFlush(user);
        System.out.println("!!!!!!!!!!!!in save: " + user.toString());
        return null;
    }

    @Override
    public UserModel getUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        UserModel userModel = new UserModel();
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
