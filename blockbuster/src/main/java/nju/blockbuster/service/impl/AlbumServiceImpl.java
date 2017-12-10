package nju.blockbuster.service.impl;

import nju.blockbuster.entities.Album;
import nju.blockbuster.entities.Photo;
import nju.blockbuster.models.AlbumModel;
import nju.blockbuster.repository.AlbumRepository;
import nju.blockbuster.repository.PhotoRepository;
import nju.blockbuster.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public AlbumModel[] getAlbum(String email) {
        List<Album> albumList = albumRepository.findAlbumsByEmail(email);
        System.out.println("album service: "  +email);
        System.out.println("albumService: " + albumList.size());
        List<AlbumModel> albumModelList = new ArrayList<>();
        for (Album album : albumList) {
            AlbumModel albumModel = new AlbumModel();
            albumModel.setTitle(album.getTitle());
            albumModel.setAid(album.getAid());
            albumModel.setEmail(album.getEmail());
            List<Photo> photos = photoRepository.findByAid(album.getAid());
            String[] photoPath = new String[photos.size()];
            for (int i = 0; i < photos.size(); i++) {
                photoPath[i] = photos.get(i).getPic();
            }
            albumModel.setPhotos(photoPath);
            albumModelList.add(albumModel);
        }
        System.out.println("albumService: " + albumModelList.size());
        AlbumModel[] albumModels = new AlbumModel[albumModelList.size()];
        for (int i = 0; i < albumModels.length; i++) {
            albumModels[i] = albumModelList.get(i);
        }
        return albumModels;
    }

    @Override
    public AlbumModel albumDetail(String aid) {
        Album album = albumRepository.findByAid(aid);
        AlbumModel albumModel = new AlbumModel();
        if(album == null || album.getAid() == null ){
            return albumModel;
        }

        albumModel.setTitle(album.getTitle());
        albumModel.setAid(album.getAid());
        albumModel.setEmail(album.getEmail());

        List<Photo> photos = photoRepository.findByAid(album.getAid());
        String[] photoPath = new String[photos.size()];
        for (int i = 0; i < photos.size(); i++) {
            photoPath[i] = photos.get(i).getPic();
        }
        albumModel.setPhotos(photoPath);

        return albumModel;
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

    @Override
    public ResultMessage deleteAlbum(String aid) {
        return null;
    }
}
