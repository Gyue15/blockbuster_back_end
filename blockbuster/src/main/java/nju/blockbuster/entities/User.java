package nju.blockbuster.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    private String username;

    @Id
    private String email;

    private String password;

    private String avatar;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object obj) {

        return obj != null && obj.getClass() == this.getClass() && this.email.equals(((User) obj).email);
    }

    @Override
    public String toString() {
        return "User [name = "
                + username
                + ", email = "
                + email
                + ", password = "
                + password
                + ", avatar = "
                + avatar
                + "]";
    }
}
