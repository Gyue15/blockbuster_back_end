package nju.blockbuster.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tagrelation")
public class TagRelation {

    @EmbeddedId
    private TagRelationPK tagRelationPK;

    public TagRelationPK getTagRelationPK() {
        return tagRelationPK;
    }

    public void setTagRelationPK(TagRelationPK tagRelationPK) {
        this.tagRelationPK = tagRelationPK;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.getClass() != obj.getClass() && this.tagRelationPK.equals(((TagRelation)obj).tagRelationPK);
    }

    @Override
    public String toString() {
        return "TagRelation [ tag = "
                + tagRelationPK.getTag()
                + ", sid = "
                + tagRelationPK.getSid()
                + "]";
    }
}
