package nju.blockbuster.repository;

import nju.blockbuster.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findAlbumsByEmail(String email);
    Album findByAid(String aid);
}
