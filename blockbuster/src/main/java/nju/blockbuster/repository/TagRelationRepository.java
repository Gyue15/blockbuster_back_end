package nju.blockbuster.repository;

import nju.blockbuster.entities.TagRelation;
import nju.blockbuster.entities.TagRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRelationRepository extends JpaRepository<TagRelation, TagRelationPK>{
    List<TagRelation> findByTagRelationPK_Sid(Integer sid);
    List<TagRelation> findByTagRelationPK_Tag(String tag);
}
