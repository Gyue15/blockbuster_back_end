package nju.blockbuster.service;

import nju.blockbuster.models.UserModel;
import util.ResultMessage;

public interface UserService {
    UserModel findUser(String email);

    ResultMessage addUser(UserModel userModel);

    UserModel getUser(String email, String password);
}
