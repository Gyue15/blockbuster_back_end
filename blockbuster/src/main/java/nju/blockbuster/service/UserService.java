package nju.blockbuster.service;

import nju.blockbuster.models.UserModel;

public interface UserService {
    UserModel findUser(String email);
}
