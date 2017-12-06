package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import nju.blockbuster.config.ConfigClass;
import nju.blockbuster.models.PhotoModel;
import nju.blockbuster.models.ShowModel;
import nju.blockbuster.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import util.ResultMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/show", produces = "application/json;charset=UTF-8")
@Controller
public class ShowController {

    private static final String PARENT_PATH = "~";

    private static final String BROKEN_PATH = "~";

    @Autowired
    private ShowService showService;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file){
        System.out.println(file);
        return ("SUCCESS");
    }

    @PostMapping("/post")
    @ResponseBody
    public String postPhoto(String[] fileNames,String title, String description, String[] tags, String albumId, String email){
        return ("SUCCESS");
    }



//    public String postPhoto(MultipartFile[] files, String title, String description, String[] tags, String albumId, String email) {
//        System.out.println(files.length);
//        System.out.println(tags);
//
//        //System.out.println(albumId == null);
//        Date date = new Date();
//        List<String> fileNames = new ArrayList<>();
//        // 存储文件
//        for (MultipartFile file : files) {
//            System.out.println(file.isEmpty());
//            if (!file.isEmpty()) {
//                try {
//                    // 文件保存路径
//                    String filePath = ConfigClass.PATH + System.currentTimeMillis() + file.getOriginalFilename();
//                    // 文件url
//                    String fileUrl = ConfigClass.URL + System.currentTimeMillis() + file.getOriginalFilename();
//                    File dest = new File(filePath);
//
//                    // 检测是否存在目录
//                    if (!dest.getParentFile().exists()) {
//                        dest.getParentFile().mkdirs();
//                    }
//
//                    file.transferTo(dest);
//                    fileNames.add(fileUrl);
//                    System.out.println("000filename size:" + fileNames.size());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                fileNames.add(BROKEN_PATH);
//                System.out.println("111filename size:" + fileNames.size());
//            }
//        }
//
//        // 存show表
//        ShowModel showModel = new ShowModel();
//        showModel.setTags(tags);
//        showModel.setDescription(description);
//        showModel.setEmail(email);
//        showModel.setLikeNum(0);
//        showModel.setDate(new java.sql.Date(date.getTime()));
//        showModel.setAid(albumId);
//        Integer sid = showService.saveShow(showModel);
//        System.out.println("1111111111111111Show Controller: " + sid);
//        if (sid < 0) {
//            return JSON.toJSONString(ResultMessage.FAILURE);
//        }
//
//        // 存Photo表
//        System.out.println("222filename size:" + fileNames.size());
//        for (int i = 0; i < fileNames.size(); i++) {
//            PhotoModel photoModel = new PhotoModel();
//            photoModel.setPic(fileNames.get(i));
//            photoModel.setAid(albumId);
//            photoModel.setSid(sid);
//            System.out.println("22222222222222Show Controller2: " + photoModel.getSid());
//            Integer pid = showService.addPhoto(photoModel);
//            if (pid < 0) {
//                return JSON.toJSONString(ResultMessage.FAILURE);
//            }
//        }
//        return JSON.toJSONString(ResultMessage.SUCCESS);
//    }

    @GetMapping("/tags")
    @ResponseBody
    public String getHotTags() {
        return JSON.toJSONString(showService.getHotTags());
    }

    @PostMapping("/hot")
    @ResponseBody
    public String getHotShow(String email, int pageNum) {
        return JSON.toJSONString(showService.getHotShows(email, pageNum));
    }

    @PostMapping("/care")
    @ResponseBody
    public String getCareShows(String email) {
        return JSON.toJSONString(showService.getCareShows(email));
    }

    @PostMapping("/like")
    @ResponseBody
    public String likeShow(String email, Integer showID) {
        // 保存show
        ShowModel showModel = showService.getShow(showID);
        showModel.setLikeNum(showModel.getLikeNum() + 1);
        showService.saveShow(showModel);

        // 保存like
        showService.saveLike(showID, email);
        return null;
    }

    @PostMapping("/search")
    @ResponseBody
    public String searchShow(String tag, String email) {
        return JSON.toJSONString(showService.searchShows(tag, email));
    }
}
