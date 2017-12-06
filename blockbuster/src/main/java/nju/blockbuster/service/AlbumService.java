package nju.blockbuster.service;

import nju.blockbuster.models.AlbumModel;

public interface AlbumService {
    AlbumModel[] getAlbum(String email);

    Boolean saveAlbum(AlbumModel albumModel);
}
