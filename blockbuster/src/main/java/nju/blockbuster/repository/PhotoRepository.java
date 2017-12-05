package nju.blockbuster.repository;

import nju.blockbuster.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    Photo findPhotoByAid(Integer aid);
}
