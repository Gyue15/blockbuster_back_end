package nju.blockbuster.models;

import com.alibaba.fastjson.JSON;

import java.util.Date;

public class MessageModel {

    private Integer mid;

    /**
     * 消息的拥有者email
     */
    private String owner;

    /**
     * true的时候是已读
     */
    private Boolean flag;

    /**
     * 消息内容
     */
    private String text;

    /**
     * 消息制造者
     */
    private String email;

    /**
     * 消息制造者
     */
    private String username;

    /**
     * 消息制造者
     */
    private String avatar;

    private Date date;

    private String formatDate;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getFormatDate() {
        return formatDate;
    }

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
