package nju.blockbuster.controller;

import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    @ResponseBody
    public String signUp(String username, String email, String password) {
        return null;
    }

    @RequestMapping("/login")
    @ResponseBody
    public String logIn(String email, String password) {
        return null;
    }

    @RequestMapping("/signout")
    @ResponseBody
    public String signOut(String email) {
        return null;
    }

}
