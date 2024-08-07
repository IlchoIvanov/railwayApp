package com.example.railwayapp.service;

import com.example.railwayapp.model.dto.CommentViewDto;
import com.example.railwayapp.model.dto.PictureShortInfoDto;
import com.example.railwayapp.model.entity.Picture;
import com.example.railwayapp.model.user.RailwayAppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface PictureService {

     List<PictureShortInfoDto> getAllPicturesByStationId(Long stationId);
     Picture getPicturebyId(Long id);
     void deletePictureById(Long id) throws IOException;

     void save(Picture picture);
     String getPictureUrlById(Long id);

     void uploadPicture(MultipartFile image, @AuthenticationPrincipal RailwayAppUserDetails userDetails, Long stationId) throws IOException;

    List<CommentViewDto> getPictureComments(Long pictureId);
}
