package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import netscape.javascript.JSObject;
import nju.blockbuster.models.PhotoModel;
import nju.blockbuster.models.ShowModel;
import nju.blockbuster.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import util.ResultMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/show")
@Controller
public class ShowController {

    private static final String PARENT_PATH = "~";

    private static final String BROKEN_PATH = "~";

    @Autowired
    private ShowService showService;

    @RequestMapping("/post")
    @ResponseBody
    public String postPhoto(MultipartFile[] files, String title, String description, String[] tags, Integer albumId, String email){

        Date date= new Date();
        List<String> fileNames = new ArrayList<>();
        // 存储文件
        for (MultipartFile file: files) {
            if (!file.isEmpty()) {
                try {
                    // 文件保存路径
                    String filePath = PARENT_PATH + "/" + email + "/" + date.toString() + "/" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                    fileNames.add(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                fileNames.add(BROKEN_PATH);
            }
        }

        // 存show表
        ShowModel showModel = new ShowModel();
        showModel.setTags(tags);
        showModel.setDescription(description);
        showModel.setEmail(email);
        showModel.setLikeNum(0);
        showModel.setDate(new java.sql.Date(date.getTime()));
        Integer sid = showService.saveShow(showModel);
        if (sid < 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }

        // 存Photo表
        for (int i = 0; i < fileNames.size(); i++) {
            PhotoModel photoModel = new PhotoModel();
            photoModel.setPic(fileNames.get(i));
            photoModel.setAid(albumId);
            photoModel.setSid(sid);
            Integer pid = showService.addPhoto(photoModel);
            if (pid < 0) {
                return JSON.toJSONString(ResultMessage.FAILURE);
            }
        }
        return JSON.toJSONString(ResultMessage.SUCCESS);
    }

    @RequestMapping("/tags")
    @ResponseBody
    public String getHotTags() {
        return JSON.toJSONString(showService.getHotTags());
    }

    @RequestMapping("/hot")
    @ResponseBody
    public String getHotShow(String email, int pageNum) {
        return JSON.toJSONString(showService.getHotShows(email, pageNum));
    }

    @RequestMapping("/care")
    @ResponseBody
    public String getCareShows(String email) {
        return JSON.toJSONString(showService.getCareShows(email));
    }

    @RequestMapping("/like")
    @ResponseBody
    public String likeShow(String email,Integer showID) {
        // 保存show
        ShowModel showModel = showService.getShow(showID);
        showModel.setLikeNum(showModel.getLikeNum() + 1);
        showService.saveShow(showModel);

        // 保存like
        showService.saveLike(showID, email);
        return null;
    }

    @RequestMapping("/search")
    @ResponseBody
    public String searchShow(String tag, String email) {
        return JSON.toJSONString(showService.searchShows(tag, email));
    }
}
