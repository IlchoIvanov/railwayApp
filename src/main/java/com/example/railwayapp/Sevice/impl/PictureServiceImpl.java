package com.example.railwayapp.Sevice.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.railwayapp.Model.Dto.PictureShortInfoDto;
import com.example.railwayapp.Model.Entity.Picture;
import com.example.railwayapp.Model.Entity.Station;
import com.example.railwayapp.Model.Entity.User;
import com.example.railwayapp.Model.User.RailwayAppUserDetails;
import com.example.railwayapp.Repository.PictureRepository;
import com.example.railwayapp.Repository.StationRepository;
import com.example.railwayapp.Repository.UserRepository;
import com.example.railwayapp.Sevice.PictureService;
import com.example.railwayapp.Sevice.StationService;
import com.example.railwayapp.Sevice.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;;
    private final StationService stationService;
    private final Cloudinary cloudinary;
    private final UserService userService;
    private final StationRepository stationRepository;
    private final UserRepository userRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, StationService stationService, Cloudinary cloudinary, UserService userService,  StationRepository stationRepository, UserRepository userRepository) {
        this.pictureRepository = pictureRepository;
        this.stationService = stationService;
        this.cloudinary = cloudinary;
        this.userService = userService;

        this.stationRepository = stationRepository;
        this.userRepository = userRepository;
    }



    @Override
    public List<PictureShortInfoDto> getAllPicturesByStationId(Long stationId) {
        Station station = stationService.getStationById(stationId);
        List<Picture> pictures = station.getPictures();
        List<PictureShortInfoDto> pictureShortInfoDtos = new ArrayList<>();
        //TODO: modelMapper
        for (Picture picture : pictures) {
            PictureShortInfoDto pictureShortInfoDto = new PictureShortInfoDto();
            pictureShortInfoDto.setId(picture.getId());
            pictureShortInfoDto.setAuthor(picture.getAuthor().getUsername());
            pictureShortInfoDto.setPath(picture.getPath());
            pictureShortInfoDtos.add(pictureShortInfoDto);
        }

        return pictureShortInfoDtos;
    }

    @Override
    public Picture getPicturebyId(Long id) {
        return pictureRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePictureById(Long id) throws IOException {
        Picture picture = pictureRepository.findById(id).orElse(null);
        Station station = picture.getStation();
        station.getPictures().remove(picture);
        stationRepository.save(station);
        User author = picture.getAuthor();
        author.getUserPictures().remove(picture);
        userRepository.save(author);
        cloudinary.uploader().destroy(picture.getCloudinaryId(), ObjectUtils.emptyMap());
        pictureRepository.deleteById(id);
    }

    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void uploadPicture(MultipartFile image,@AuthenticationPrincipal RailwayAppUserDetails userDetails, Long stationId) throws IOException {
        Map result = cloudinary.uploader().upload(this.convert(image), ObjectUtils.emptyMap());
        Picture picture = new Picture();
        picture.setDate(LocalDate.now());
        picture.setLikes(0);
        picture.setPath((String) result.get("url"));
        Station station = stationService.getStationById(stationId);
        if (station == null) {
            throw new IOException();
            //TODO
        }
        picture.setStation(station);
        picture.setCloudinaryId((String) result.get("public_id"));
        picture.setComments(new ArrayList<>());
        User author = userService.findUserByUsername(userDetails.getName());
        picture.setAuthor(author);
        author.getUserPictures().add(picture);
        if(!author.getVisitedStations().contains(station)){
            author.getVisitedStations().add(station);
        }
        userRepository.save(author);
        this.save(picture);
        station.getPictures().add(picture);
        stationRepository.save(station);

    }
    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }


}
