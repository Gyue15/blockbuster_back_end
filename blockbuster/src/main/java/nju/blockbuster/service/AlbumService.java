package nju.blockbuster.service;

import nju.blockbuster.models.AlbumModel;
import util.ResultMessage;

public interface AlbumService {
    AlbumModel[] getAlbum(String email);

    AlbumModel albumDetail(String aid);

    Boolean saveAlbum(AlbumModel albumModel);

    ResultMessage deleteAlbum(String aid);
}
