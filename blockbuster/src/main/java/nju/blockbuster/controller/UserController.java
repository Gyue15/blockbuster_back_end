package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import nju.blockbuster.models.UserModel;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ResultMessage;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final String AVATAR = "~/web_project/avatar/avatar.jpg";

    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    @ResponseBody
    public String signUp(String username, String email, String password) {
        //检查是否重复
        UserModel userModel = userService.getUser(email, password);
        if (userModel != null && userModel.getEmail() != null && userModel.getEmail().length() != 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }

        userModel = new UserModel();
        userModel.setAvatar(AVATAR);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setUsername(username);
        ResultMessage res = userService.addUser(userModel);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/login")
    @ResponseBody
    public String logIn(String email, String password) {
        UserModel userModel = userService.getUser(email, password);
        if (userModel == null || userModel.getEmail() == null || userModel.getEmail().length() == 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }
        return JSON.toJSONString(userModel);
    }

    @RequestMapping("/signout")
    @ResponseBody
    public String signOut(String email) {
        UserModel userModel = userService.findUser(email);
        if (userModel == null || userModel.getEmail() == null || userModel.getEmail().length() == 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }
        return JSON.toJSONString(userModel);
    }

    @RequestMapping("/follow")
    @ResponseBody
    public String follow(String followerEmail, String followedEmail) {
        return JSON.toJSONString(userService.follow(followerEmail, followedEmail));
    }

    @RequestMapping("/getfollowed")
    @ResponseBody
    public String getFollowed(String email) {
        return JSON.toJSONString(userService.getFollowedUser(email));
    }

    @RequestMapping("/isFollowed")
    @ResponseBody
    public Boolean isFollow(String followerEmail, String followedEmail) {
        return userService.isFollow(followerEmail, followedEmail);
    }


}
