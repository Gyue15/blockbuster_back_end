package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import nju.blockbuster.models.AlbumModel;
import nju.blockbuster.models.PhotoModel;
import nju.blockbuster.models.ShowModel;
import nju.blockbuster.models.UserModel;
import nju.blockbuster.service.AlbumService;
import nju.blockbuster.service.ShowService;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShowService showService;

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/")
    @ResponseBody
    public String test() {
        UserModel userModel1 = new UserModel();
        userModel1.setUsername("test");
        userModel1.setPassword("1234567");
        userModel1.setEmail("test@gmail.com");
        userModel1.setAvatar("/test/test");
        userService.addUser(userModel1);

        UserModel userModel2 = new UserModel();
        userModel2.setUsername("test");
        userModel2.setPassword("123456");
        userModel2.setEmail("test123@gmail.com");
        userModel2.setAvatar("/test/test233");
        userService.addUser(userModel2);

        String[] tags = {"111", "222", "333"};

        System.out.println("????json: " + JSON.toJSONString(tags));

        PhotoModel photoModel = new PhotoModel();
        photoModel.setSid(0);
        photoModel.setAid(3);
        photoModel.setPic("/2333/2333.jpg");
        int i = showService.addPhoto(photoModel);
        System.out.println("!!!!pid: " + i);

        AlbumModel[] models = albumService.getAlbum("test@gmail.com");
        for (AlbumModel model: models) {
            System.out.println(model.toString());
        }

        tags = showService.getHotTags();
        System.out.println("tags size: " + tags.length);
        for (String tag: tags) {
            System.out.println("TAG!!!!!" + tag);
        }

        ShowModel[] showModels = showService.getCareShows("abc@gmail.com");
        System.out.println("show size: " + showModels.length);
        for (ShowModel showModel: showModels) {
            System.out.println(showModel.toString());
        }

        ShowModel showModel = new ShowModel();
        showModel.setAid(1);
        showModel.setDescription("2333333");
        showModel.setEmail("233@gmail.com");
        showModel.setLikeNum(0);
        showModel.setDate(new Date());
        showService.saveShow(showModel);


        return userService.findUser("test@gmail.com").getUsername();


    }
}
