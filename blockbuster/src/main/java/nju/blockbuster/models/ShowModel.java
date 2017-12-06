package nju.blockbuster.models;

import java.util.Date;

public class ShowModel {

    private Integer sid;

    private Integer aid;

    private String description;

    private Date date;

    private Integer likeNum;

    private String email;

    private Boolean isLiked;

    private String[] tags;

    private String[] pictures;

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

    public Boolean getLiked() {
        return isLiked;
    }

    public String[] getTags() {
        return tags;
    }

    public Integer getAid() {
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

    public void setAid(Integer aid) {
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
