package nju.blockbuster.service.impl;

import nju.blockbuster.entities.*;
import nju.blockbuster.models.PhotoModel;
import nju.blockbuster.models.ShowModel;
import nju.blockbuster.repository.*;
import nju.blockbuster.service.ShowService;
import nju.blockbuster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import util.ResultMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TagRelationRepository tagRelationRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Integer saveShow(ShowModel showModel) {
        Show show = new Show();
        show.setAid(showModel.getAid());
        show.setDate(showModel.getDate());
        show.setDescription(showModel.getDescription());
        show.setEmail(showModel.getEmail());
        show.setLikeNum(showModel.getLikeNum());
        show.setSid(showModel.getSid());
        show.setTitle(showModel.getTitle());
        show = showRepository.save(show);
        for (int i = 0; i < showModel.getTags().length; i++) {
            String tag = showModel.getTags()[i];
            Tags tags = tagsRepository.findByTag(tag);
            if (tags == null) {
                tags = new Tags();
                tags.setUsedTime(0);
            }
            tags.setTag(tag);
            tags.setUsedTime(tags.getUsedTime() + 1);
            tagsRepository.saveAndFlush(tags);
            TagRelation tagRelation = new TagRelation();
            TagRelationPK tagRelationPK = new TagRelationPK();
            tagRelationPK.setSid(show.getSid());
            tagRelationPK.setTag(tag);
            tagRelation.setTagRelationPK(tagRelationPK);
            tagRelationRepository.saveAndFlush(tagRelation);
        }

        List<Follow> followList = followRepository.findByFollowPK_FollowedEmail(show.getEmail());
        List<Message> messagesList = new ArrayList<>();
        for (Follow follow: followList) {
            User user = userRepository.findUserByEmail(follow.getFollowPK().getFollowerEmail());
            Message message = new Message();
            message.setText("发布了一个大片秀：" + show.getTitle());
            message.setUsername(user.getUsername());
            message.setOwner(show.getEmail());
            message.setFlag(false);
            message.setAvatar(user.getAvatar());
            message.setEmail(user.getEmail());
            message.setDate(new Date());
            messagesList.add(message);
        }
        messageRepository.save(messagesList);

        return show.getSid();
    }

    @Override
    public Integer addPhoto(PhotoModel photoModel) {
        Photo photo = new Photo();
        photo.setPic(photoModel.getPic());
        photo.setSid(photoModel.getSid());
        photo.setAid(photoModel.getAid());
        System.out.println(photoModel.toString());
        System.out.println(photo.toString());
        System.out.println("!!!!!!!!!!!!!!!!!!!+++++++++++++++show service1: " + photo.getSid());
        photo = photoRepository.save(photo);
        System.out.println("++++++++========================show service2: " + photo.getSid());
        return photo.getPid();
    }

    @Override
    public String[] getHotTags() {
        Pageable pageable = new PageRequest(0, 6, Sort.Direction.DESC, "usedTime");
        Page<Tags> tagsPage = tagsRepository.findByUsedTimeGreaterThanEqual(0, pageable);
        List<Tags> tagsList = tagsPage.getContent();
        String[] tags = new String[tagsList.size()];
        for (int i = 0; i < tags.length; i++) {
            tags[i] = tagsList.get(i).getTag();
        }
        return tags;
    }

    @Override
    public ShowModel[] getHotShows(String email, int pageNum) {
        Pageable pageable = new PageRequest(pageNum, 20, Sort.Direction.DESC, "likeNum");
        Page<Show> showPage = showRepository.findByLikeNumGreaterThanEqual(0, pageable);
        List<Show> showList = showPage.getContent();

        return this.toShowModels(email, showList);
    }

    @Override
    public ShowModel[] getCareShows(String email) {

        List<Follow> followList = followRepository.findByFollowPK_FollowerEmail(email);

        List<Show> showList = new ArrayList<>();
        // 获得所有的show
        for (Follow follow : followList) {
            showList.addAll(showRepository.findByEmail(follow.getFollowPK().getFollowedEmail()));
        }

        // 按时间排序
        sort(showList);

        return this.toShowModels(email, showList);
    }

    @Override
    public ResultMessage saveLike(Integer sid, String email) {
        LikePK likePK = new LikePK();
        likePK.setSid(sid);
        likePK.setEmail(email);

        Like like = likeRepository.findLikesByLikePK(likePK);
        if (like != null && like.getLikePK() != null) {
            return ResultMessage.FAILURE;
        }
        like = new Like();
        like.setLikePK(likePK);

        likeRepository.save(like);
        Show show = showRepository.findShowBySid(sid);
        show.setLikeNum(show.getLikeNum()+1);
        showRepository.saveAndFlush(show);

        if (email.equals(show.getEmail())) {
            return ResultMessage.SUCCESS;
        }

        // 存储消息
        User user = userRepository.findUserByEmail(email);
        Message  message = new Message();
        message.setFlag(false);
        message.setAvatar(user.getAvatar());
        message.setEmail(user.getEmail());
        message.setOwner(email);
        message.setText("点赞了你的大片秀：" + show.getTitle());
        message.setUsername(user.getUsername());
        message.setDate(new Date());
        messageRepository.save(message);

        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteLike(Integer sid, String email) {
        LikePK likePK = new LikePK();
        likePK.setSid(sid);
        likePK.setEmail(email);

        Like like = likeRepository.findLikesByLikePK(likePK);
        if (like == null || like.getLikePK() == null) {
            return ResultMessage.FAILURE;
        }
        like.setLikePK(likePK);

        likeRepository.delete(like);
        Show show = showRepository.findShowBySid(sid);
        show.setLikeNum(show.getLikeNum()-1);
        showRepository.saveAndFlush(show);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ShowModel getShow(Integer sid, String email) {
        Show show = showRepository.findShowBySid(sid);
        return toShowModel(email, show);
    }

    @Override
    public ShowModel[] getMyShow(String email, String visitorEmail) {
        List<Show> showList = showRepository.findByEmail(email);

        if (email.equals(visitorEmail)) {
            return toShowModels(email, showList);
        }

        // 存储消息
        User user = userRepository.findUserByEmail(visitorEmail);
        Message message = new Message();
        message.setAvatar(user.getAvatar());
        message.setFlag(false);
        message.setEmail(user.getEmail());
        message.setOwner(email);
        message.setUsername(user.getUsername());
        message.setText("访问了你的主页");
        message.setDate(new Date());
        messageRepository.save(message);


        return toShowModels(email, showList);
    }

    @Override
    public ShowModel[] searchShows(String key, String email) {
        key = "%" + key + "%";
        List<TagRelation> tagRelationList = tagRelationRepository.findByTagRelationPK_TagLike(key);

        List<Show> showList = new ArrayList<>();
        // 获得所有的show
        for (TagRelation tagRelation : tagRelationList) {
            Show show = showRepository.findShowBySid(tagRelation.getTagRelationPK().getSid());
            if (!showList.contains(show)) {
                showList.add(show);
            }
        }

        List<Show> addShows = showRepository.findByTitleLikeOrDescriptionLike(key, key);
        for (Show show: addShows) {
            if (!showList.contains(show)) {
                showList.add(show);
            }
        }

        // 按时间排序
        sort(showList);

        return this.toShowModels(email, showList);
    }

    private void sort(List<Show> showList) {
        for (int i = showList.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (showList.get(j + 1).getDate().getTime() > showList.get(j).getDate().getTime()) {
                    Show temp = showList.get(j);
                    showList.set(j, showList.get(j + 1));
                    showList.set(j + 1, temp);
                }
            }
        }
    }

    private ShowModel toShowModel(String email, Show show) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ShowModel showModel = new ShowModel();
        showModel.setLikeNum(show.getLikeNum());
        showModel.setDate(show.getDate());
        showModel.setEmail(show.getEmail());
        showModel.setDescription(show.getDescription());
        showModel.setSid(show.getSid());
        showModel.setAid(show.getAid());
        showModel.setTitle(show.getTitle());

        // tags
        List<TagRelation> tagList = tagRelationRepository.findByTagRelationPK_Sid(show.getSid());
        String[] tags = new String[tagList.size()];
        for (int j = 0; j < tags.length; j++) {
            tags[j] = tagList.get(j).getTagRelationPK().getTag();
        }
        showModel.setTags(tags);

        // is liked
        LikePK likePk = new LikePK();
        likePk.setEmail(email);
        likePk.setSid(show.getSid());
        Like like = likeRepository.findLikesByLikePK(likePk);
        if (like != null && like.getLikePK().getSid() != null) {
            showModel.setLiked(true);
        } else {
            showModel.setLiked(false);
        }

        // pictures
        List<Photo> photoList = photoRepository.findBySid(show.getSid());
        String[] pictures = new String[photoList.size()];
        for (int j = 0; j < pictures.length; j++) {
            pictures[j] = photoList.get(j).getPic();
        }
        showModel.setPictures(pictures);

        // is followed
        showModel.setFollowed(userService.isFollow(email, show.getEmail()));

        // user
        User user = userRepository.findUserByEmail(show.getEmail());
        showModel.setAvatar(user.getAvatar());
        showModel.setUserName(user.getUsername());

        // date
        showModel.setFormatDate(df.format(show.getDate()));

        // album name
        Album album = albumRepository.findByAid(show.getAid());
        if(album != null){
            showModel.setAlbumName(album.getTitle());
        }else {
            showModel.setAlbumName("");
        }

        return showModel;

    }

    private ShowModel[] toShowModels(String email, List<Show> showList) {
        ShowModel[] showModels = new ShowModel[showList.size()];
        for (int i = 0; i < showModels.length; i++) {
            showModels[i] = toShowModel(email, showList.get(i));
        }
        return showModels;
    }
}
