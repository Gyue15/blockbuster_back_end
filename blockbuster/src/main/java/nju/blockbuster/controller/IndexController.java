package nju.blockbuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    UserController userController;

    @RequestMapping("/")
    @ResponseBody
    public String test() {
        System.out.println(userController.hasNewMessage("1@1.com"));
        System.out.println(userController.getMessage("1@1.com"));
        System.out.println(userController.hasNewMessage("1@1.com"));
        return "hello";
    }
}
