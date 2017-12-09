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

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    private static Map<String, String> filePathMap;

    @Autowired
    private UserService userService;

    @Autowired
    private AlbumController albumController;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(String username, String email, String password) {
        //检查是否重复
        UserModel userModel = userService.getUser(email, password);
        if (userModel != null && userModel.getEmail() != null && userModel.getEmail().length() != 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }

        userModel = new UserModel();
        userModel.setAvatar(ConfigClass.AVATAR_URL + ConfigClass.AVATAR_NAME);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setUsername(username);
        ResultMessage res = userService.addUser(userModel);
        albumController.createNewAlbum(email, "默认专辑");
        System.out.println("signUpResult:" + JSON.toJSONString(res));
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String logIn(String email, String password) {
        System.out.println("login:" + email + "  " + password);
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
        return JSON.toJSONString(ResultMessage.SUCCESS);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        if (filePathMap == null) {
            filePathMap = new TreeMap<>();
        }
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = ConfigClass.AVATAR_PATH + System.currentTimeMillis() + file.getOriginalFilename();
                // 文件url
                String fileUrl = ConfigClass.AVATAR_URL + System.currentTimeMillis() + file.getOriginalFilename();
                File dest = new File(filePath);

                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }

                file.transferTo(dest);
                filePathMap.put(file.getOriginalFilename(), fileUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(file);
        return "SUCCESS";
    }

    @PostMapping("/postAvatar")
    @ResponseBody
    public String postAvatar(String fileName, String email) {
        String url = filePathMap.get(fileName);
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setAvatar(url);
        ResultMessage res = userService.modifyUser(userModel);
        if (res != ResultMessage.SUCCESS) {
            return JSON.toJSONString(res);
        }
        return url;
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public String changePassword(String email, String oldPassword, String newPassword) {
        UserModel userModel = userService.getUser(email, oldPassword);
        if (userModel == null || userModel.getEmail() == null) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }
        userModel.setEmail(email);
        userModel.setPassword(newPassword);
        ResultMessage res = userService.modifyUser(userModel);
        if (res != ResultMessage.SUCCESS) {
            return JSON.toJSONString(res);
        }
        return JSON.toJSONString(res);
    }

    @PostMapping("/follow")
    @ResponseBody
    public String follow(String email, String followedEmail) {
        return JSON.toJSONString(userService.follow(email, followedEmail));
    }

    @PostMapping("/unfollow")
    @ResponseBody
    public String unfollow(String email, String followedEmail) {
        return JSON.toJSONString(userService.unfollow(email, followedEmail));
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
