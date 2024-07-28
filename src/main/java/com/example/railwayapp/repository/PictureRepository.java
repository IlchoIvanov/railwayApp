package com.example.railwayapp.repository;

import com.example.railwayapp.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepository extends JpaRepository<Picture, Long >{

}
