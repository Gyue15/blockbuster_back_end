package nju.blockbuster.service.impl;

import nju.blockbuster.entities.Follow;
import nju.blockbuster.entities.FollowPK;
import nju.blockbuster.entities.User;
import nju.blockbuster.models.UserModel;
import nju.blockbuster.repository.FollowRepository;
import nju.blockbuster.repository.UserRepository;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

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

    @Override
    public List<UserModel> getFollowedUser(String email) {
        List<Follow> followList = followRepository.findByFollowPK_FollowerEmail(email);
        List<UserModel> userModelList = new ArrayList<>();
        for (Follow follow: followList) {
            User user = userRepository.findUserByEmail(follow.getFollowPK().getFollowedEmail());
            UserModel userModel = new UserModel();
            if (user == null) {
                continue;
            }
            userModel.setAvatar(user.getAvatar());
            userModel.setEmail(user.getEmail());
            userModel.setPassword(user.getPassword());
            userModel.setUsername(user.getUsername());
            userModelList.add(userModel);
        }
        return userModelList;
    }

    @Override
    public ResultMessage follow(String followerEmail, String followedEmail) {
        FollowPK followPK = new FollowPK();
        followPK.setFollowedEmail(followedEmail);
        followPK.setFollowerEmail(followerEmail);
        Follow follow = new Follow();
        follow.setFollowPK(followPK);
        followRepository.save(follow);

        return ResultMessage.SUCCESS;
    }

}
