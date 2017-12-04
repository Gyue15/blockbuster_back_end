package nju.blockbuster.controller;

import nju.blockbuster.service.ShowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/show")
@Controller
public class ShowController {

    private ShowService showService;

    @RequestMapping("/post")
    @ResponseBody
    public String postPhoto(MultipartFile[] files, String title, String description, String[] tags, String album){
        return null;
    }

    @RequestMapping("/tags")
    @ResponseBody
    public String getHotTags() {
        return null;
    }

    @RequestMapping("/hot")
    @ResponseBody
    public String getHotShow() {
        return null;
    }

    @RequestMapping("/care")
    @ResponseBody
    public String getCareShows(String email) {
        return null;
    }

    @RequestMapping("/like")
    @ResponseBody
    public String likeShow(String email,String showID) {
        return null;
    }

    @RequestMapping("/search")
    @ResponseBody
    public String searchShow(String tag) {
        return null;
    }
}
