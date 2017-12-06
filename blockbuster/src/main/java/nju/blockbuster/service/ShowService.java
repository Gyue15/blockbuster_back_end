package nju.blockbuster.service;

import nju.blockbuster.models.PhotoModel;
import nju.blockbuster.models.ShowModel;
import util.ResultMessage;

public interface ShowService {

    Integer saveShow(ShowModel showModel);

    Integer addPhoto(PhotoModel photoModel);

    String[] getHotTags();

    ShowModel[] getHotShows(String email, int pageNum);

    ShowModel[] getCareShows(String email);

    ResultMessage saveLike(Integer sid, String email);

    ShowModel[] searchShows(String tag, String email);

}
