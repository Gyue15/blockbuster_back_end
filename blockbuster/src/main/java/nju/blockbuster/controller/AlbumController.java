package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import nju.blockbuster.models.AlbumModel;
import nju.blockbuster.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ResultMessage;

@RequestMapping(value = "/album", produces = "application/json;charset=UTF-8")
@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/get")
    @ResponseBody
    public String getAlbums(String email) {
        String res = JSON.toJSONString(albumService.getAlbum(email));
        System.out.println(res);
        return res;
    }

    @PostMapping("/detail")
    @ResponseBody
    public String getAlbumDetail(String aid) {
        return JSON.toJSONString(albumService.albumDetail(aid));
    }

    @PostMapping("/create")
    @ResponseBody
    public String createNewAlbum(String email, String album) {
        System.out.println("createNewAlbum:"+email+" "+album);
        AlbumModel albumModel = new AlbumModel();
        albumModel.setEmail(email);
        albumModel.setTitle(album);
        albumModel.setAid(email + album);
        boolean res = albumService.saveAlbum(albumModel);
        return res ? JSON.toJSONString(ResultMessage.SUCCESS) : JSON.toJSONString(ResultMessage.FAILURE);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteAlbum(String aid) {
        String res = JSON.toJSONString(albumService.deleteAlbum(aid));
        System.out.println("delete");
        System.out.println(aid);
        System.out.println(res);
        return res;
    }
}
