package nju.blockbuster.service;

import nju.blockbuster.models.UserModel;
import util.ResultMessage;

import java.util.List;

public interface UserService {
    UserModel findUser(String email);

    ResultMessage addUser(UserModel userModel);

    UserModel getUser(String email, String password);

    List<UserModel> getFollowedUser(String email);

    ResultMessage follow(String followerEmail, String followedEmail);

    Boolean isFollow(String followerEmail, String followedEmail);
}
