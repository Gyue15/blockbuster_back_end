package nju.blockbuster.service;

import nju.blockbuster.models.AlbumModel;

public interface AlbumService {
    AlbumModel[] getAlbum(String email);

    Integer saveAlbum(AlbumModel albumModel);
}
