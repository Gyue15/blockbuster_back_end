package nju.blockbuster.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LikePK implements Serializable {

    private Integer sid;

    private String email;

    public Integer getSid() {
        return sid;
    }

    public String getEmail() {
        return email;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sid == null) ? 0 : sid.hashCode());
        result = prime * result
                + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null
                && this.getClass() == obj.getClass()
                && this.email.equals(((LikePK) obj).email)
                && this.sid.equals(((LikePK) obj).sid);
    }
}
