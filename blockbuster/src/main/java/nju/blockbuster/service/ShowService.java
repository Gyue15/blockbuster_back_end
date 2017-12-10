package nju.blockbuster.service;

import nju.blockbuster.models.PhotoModel;
import nju.blockbuster.models.ShowModel;
import util.ResultMessage;

import java.util.List;

public interface ShowService {

    Integer saveShow(ShowModel showModel);

    Integer addPhoto(PhotoModel photoModel);

    String[] getHotTags();

    ShowModel[] getHotShows(String email, int pageNum);

    ShowModel[] getCareShows(String email);

    ResultMessage saveLike(Integer sid, String email);

    ResultMessage deleteLike(Integer sid, String email);

    ShowModel getShow(Integer sid, String email);

    ShowModel[] getMyShow(String email, String visitorEmail);

    ShowModel[] searchShows(String key, String email);

}
