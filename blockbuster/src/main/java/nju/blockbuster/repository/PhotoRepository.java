package nju.blockbuster.repository;

import nju.blockbuster.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    Photo findPhotoByAid(Integer aid);

    List<Photo> findBySid(Integer sid);
}
