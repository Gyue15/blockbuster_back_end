package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import nju.blockbuster.config.ConfigClass;
import nju.blockbuster.models.AlbumModel;
import nju.blockbuster.models.PhotoModel;
import nju.blockbuster.models.ShowModel;
import nju.blockbuster.service.AlbumService;
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
import java.util.*;

@RequestMapping(value = "/show", produces = "application/json;charset=UTF-8")
@Controller
public class ShowController {

    private static Map<String, String> filePathMap;

    @Autowired
    private ShowService showService;

    @Autowired
    private AlbumService albumService;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        if (filePathMap == null) {
            filePathMap = new TreeMap<>();
        }
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = ConfigClass.PATH + System.currentTimeMillis() + file.getOriginalFilename();
                // 文件url
                String fileUrl = ConfigClass.URL + System.currentTimeMillis() + file.getOriginalFilename();
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

    @PostMapping("/post")
    @ResponseBody
    public String postPhoto(String[] fileNames, String title, String description, String[] tags, String albumTitle, String email) {
        Date date = new Date();
        List<String> fileUrls = new ArrayList<>();
        for (Map.Entry<String, String> entry : filePathMap.entrySet()) {
            for (String fileName : fileNames) {
                if (fileName.equals(entry.getKey())) {
                    fileUrls.add(entry.getValue());
                }
            }
        }

        String albumId = albumTitle + email;
        // 查看是否有album，如果没有就创建
        AlbumModel albumModel = albumService.albumDetail(albumId);
        if (albumId == null || albumModel.getAid() == null) {
            albumModel.setEmail(email);
            albumModel.setTitle(albumTitle);
            albumModel.setAid(albumId);
            albumService.saveAlbum(albumModel);
        }

        // 存show表
        ShowModel showModel = new ShowModel();
        showModel.setTags(tags);
        showModel.setDescription(description);
        showModel.setEmail(email);
        showModel.setLikeNum(0);
        showModel.setDate(date);
        showModel.setAid(albumId);
        showModel.setTitle(title);
        Integer sid = showService.saveShow(showModel);
        System.out.println("1111111111111111Show Controller: " + sid);
        if (sid < 0) {
            return JSON.toJSONString(ResultMessage.FAILURE);
        }

        // 存Photo表
        System.out.println("222filename size:" + fileUrls.size());
        for (int i = 0; i < fileUrls.size(); i++) {
            PhotoModel photoModel = new PhotoModel();
            photoModel.setPic(fileUrls.get(i));
            photoModel.setAid(albumId);
            photoModel.setSid(sid);
            System.out.println("22222222222222Show Controller2: " + photoModel.getSid());
            Integer pid = showService.addPhoto(photoModel);
            if (pid < 0) {
                return JSON.toJSONString(ResultMessage.FAILURE);
            }
        }
        return JSON.toJSONString(ResultMessage.SUCCESS);
    }


    @GetMapping("/tags")
    @ResponseBody
    public String getHotTags() {
        return JSON.toJSONString(showService.getHotTags());
    }

    @PostMapping("/hot")
    @ResponseBody
    public String getHotShow(String email, int pageNum) {
        System.out.println("getHotShow:" + email);
        String res = JSON.toJSONString(showService.getHotShows(email, pageNum));
        System.out.println(res);
        return res;
    }

    @PostMapping("/myShow")
    @ResponseBody
    public String getMyShow(String email) {
        return JSON.toJSONString(showService.getMyShow(email));
    }

    @PostMapping("/care")
    @ResponseBody
    public String getCareShows(String email) {
        return JSON.toJSONString(showService.getCareShows(email));
    }

    @PostMapping("/like")
    @ResponseBody
    public String likeShow(String email, Integer sid) {
        return JSON.toJSONString(showService.saveLike(sid, email));
    }

    @PostMapping("/unlike")
    @ResponseBody
    public String unlikeShow(String email, Integer sid) {
        return JSON.toJSONString(showService.deleteLike(sid, email));
    }

    @PostMapping("/detail")
    @ResponseBody
    public String showDetail(String email, Integer sid) {
        return JSON.toJSONString(showService.getShow(sid, email));
    }

    @PostMapping("/search")
    @ResponseBody
    public String searchShow(String key, String email) {
        return JSON.toJSONString(showService.searchShows(key, email));
    }
}
