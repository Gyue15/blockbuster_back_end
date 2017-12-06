package nju.blockbuster.service.impl;

import nju.blockbuster.entities.Album;
import nju.blockbuster.models.AlbumModel;
import nju.blockbuster.repository.AlbumRepository;
import nju.blockbuster.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public AlbumModel[] getAlbum(String email) {
        List<Album> albumList = albumRepository.findAlbumsByEmail(email);
        List<AlbumModel> albumModelList = new ArrayList<>();
        for (Album album: albumList) {
            AlbumModel albumModel = new AlbumModel();
            albumModel.setTitle(album.getTitle());
            albumModel.setAid(album.getAid());
            albumModel.setEmail(album.getEmail());
            albumModelList.add(albumModel);
        }
        AlbumModel[] albumModels = new AlbumModel[albumModelList.size()];
        for (int i = 0; i < albumModels.length; i++) {
            albumModels[i] = albumModelList.get(i);
        }
        return albumModels;
    }

    @Override
    public Boolean saveAlbum(AlbumModel albumModel) {
        Album album = new Album();
        album.setAid(albumModel.getAid());
        album.setEmail(albumModel.getEmail());
        album.setTitle(albumModel.getTitle());
        Album test = albumRepository.findByAid(album.getAid());
        if (test != null && test.getAid() != null) {
            return false;
        }
        album = albumRepository.saveAndFlush(album);

        if (album == null || album.getAid() == null) {
            return false;
        }
        return true;
    }
}
