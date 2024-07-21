package com.example.railwayapp.Repository;

import com.example.railwayapp.Model.Entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepository extends JpaRepository<Picture, Long >{

}
