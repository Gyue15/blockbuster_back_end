package nju.blockbuster.models;

import java.util.Date;

public class ShowModel {

    private Integer sid;

    private String title;

    private String aid;

    private String description;

    private Date date;

    private Integer likeNum;

    private String email;

    private Boolean isLiked;

    private String[] tags;

    private String[] pictures;

    private String userName;

    private String avatar;

    private Boolean isFollowed;

    private String formatDate;

    private String albumName;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFollowed(Boolean followed) {
        isFollowed = followed;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public Boolean getFollowed() {
        return isFollowed;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public String getAlbumName() {
        return albumName;
    }

    public Integer getSid() {
        return sid;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public String[] getTags() {
        return tags;
    }

    public String getAid() {
        return aid;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj == null) || this.sid.equals(((ShowModel)obj).sid);
    }

    @Override
    public String toString() {
        return "Show [sid = "
                + sid
                + ", description = "
                + description
                + ", date = "
                + date.toString()
                + ", likeNum = "
                + likeNum
                + ", email = "
                + email
                + ", isLiked = "
                + isLiked.toString()
                + ", aid = "
                + aid
                + "]";
    }
}
