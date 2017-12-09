package nju.blockbuster.models;

public class AlbumModel {

    private String aid;

    private String title;

    private String email;

    private String[] photos;

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public String getAid() {
        return aid;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj){
        return obj != null && obj.getClass() == this.getClass() && this.aid.equals(((AlbumModel)obj).aid);
    }

    @Override
    public String toString() {
        return "Album [aid = "
                + aid
                + ", title = "
                + title
                + ", email = "
                + email
                + "]";
    }
}
