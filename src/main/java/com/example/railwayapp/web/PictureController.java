package com.example.railwayapp.web;

import com.example.railwayapp.model.user.RailwayAppUserDetails;
import com.example.railwayapp.service.PictureService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PictureController {
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping("/stations/add_image/{station_id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String addImage(@PathVariable("station_id") Long stationId,@AuthenticationPrincipal RailwayAppUserDetails userDetails, @RequestParam MultipartFile image) throws IOException {
        pictureService.uploadPicture(image,userDetails, stationId);
        return "redirect:/stations/" + stationId;
    }
    @DeleteMapping("/delete/picture/{id}/{station-id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String deletePicture(@PathVariable Long id, @PathVariable("station-id") Long stationId)
            throws IOException {

        pictureService.deletePictureById(id);
        return "redirect:/stations/"+stationId;

    }

}
