package nju.blockbuster.controller;

import com.alibaba.fastjson.JSON;
import nju.blockbuster.models.AlbumModel;
import nju.blockbuster.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/album")
@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/get")
    @ResponseBody
    public String getAlbums(String email) {
        return JSON.toJSONString(albumService.getAlbum(email));
    }

    @RequestMapping("/create")
    @ResponseBody
    public Integer createNewAlbum(String email, String album) {
        AlbumModel albumModel = new AlbumModel();
        albumModel.setEmail(email);
        albumModel.setTitle(album);

        return albumService.saveAlbum(albumModel);
    }
}
