package nju.blockbuster.controller;

import nju.blockbuster.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/album")
@Controller
public class AlbumController {

    private AlbumService albumService;

    @RequestMapping("/get")
    @ResponseBody
    public String getAlbums(String email) {
        return null;
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createNewAlbum(String email, String album) {
        return null;
    }
}
