package com.example.railwayapp.Sevice;

import com.example.railwayapp.Model.Dto.PictureShortInfoDto;
import com.example.railwayapp.Model.Entity.Picture;
import com.example.railwayapp.Model.User.RailwayAppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface PictureService {

     List<PictureShortInfoDto> getAllPicturesByStationId(Long stationId);
     Picture getPicturebyId(Long id);
     void deletePictureById(Long id) throws IOException;

     void save(Picture picture);

     void uploadPicture(MultipartFile image, @AuthenticationPrincipal RailwayAppUserDetails userDetails, Long stationId) throws IOException;
}
