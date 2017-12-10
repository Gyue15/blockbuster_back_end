package nju.blockbuster.models;

import com.alibaba.fastjson.JSON;

public class MessageModel {

    private Integer mid;

    private String owner;

    private Boolean flag;

    private String text;

    private String email;

    private String username;

    private String avatar;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getMid() {
        return mid;
    }

    public String getOwner() {
        return owner;
    }

    public String getText() {
        return text;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && this.mid.equals(((MessageModel) obj).mid);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
