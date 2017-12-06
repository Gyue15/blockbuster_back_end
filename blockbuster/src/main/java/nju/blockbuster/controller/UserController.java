package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import nju.blockbuster.config.ConfigClass;
import nju.blockbuster.models.UserModel;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import util.ResultMessage;

@Controller
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(String username, String email, String password) {
        //检查是否重复
        UserModel userModel = userService.getUser(email, password);
        if (userModel != null && userModel.getEmail() != null && userModel.getEmail().length() != 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }

        userModel = new UserModel();
        userModel.setAvatar(ConfigClass.AVATAR_PATH);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setUsername(username);
        ResultMessage res = userService.addUser(userModel);
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String logIn(String email, String password) {
        UserModel userModel = userService.getUser(email, password);
        if (userModel == null || userModel.getEmail() == null || userModel.getEmail().length() == 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }
        return JSON.toJSONString(userModel);
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    @ResponseBody
    public String signOut(String email) {
        UserModel userModel = userService.findUser(email);
        if (userModel == null || userModel.getEmail() == null || userModel.getEmail().length() == 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }
        return JSON.toJSONString(userModel);
    }

    @PostMapping("/follow")
    @ResponseBody
    public String follow(String followerEmail, String followedEmail) {
        return JSON.toJSONString(userService.follow(followerEmail, followedEmail));
    }

    @PostMapping("/getfollowed")
    @ResponseBody
    public String getFollowed(String email) {
        return JSON.toJSONString(userService.getFollowedUser(email));
    }

    @PostMapping("/isFollowed")
    @ResponseBody
    public Boolean isFollow(String followerEmail, String followedEmail) {
        return userService.isFollow(followerEmail, followedEmail);
    }


}
