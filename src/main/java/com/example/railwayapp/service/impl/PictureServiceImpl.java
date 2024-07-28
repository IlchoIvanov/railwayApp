package com.example.railwayapp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.railwayapp.model.dto.CommentViewDto;
import com.example.railwayapp.model.dto.PictureShortInfoDto;
import com.example.railwayapp.model.entity.Comment;
import com.example.railwayapp.model.entity.Enum.Level;
import com.example.railwayapp.model.entity.Picture;
import com.example.railwayapp.model.entity.Station;
import com.example.railwayapp.model.entity.User;
import com.example.railwayapp.model.user.RailwayAppUserDetails;
import com.example.railwayapp.repository.PictureRepository;
import com.example.railwayapp.repository.StationRepository;
import com.example.railwayapp.repository.UserRepository;
import com.example.railwayapp.service.PictureService;
import com.example.railwayapp.service.StationService;
import com.example.railwayapp.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
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
    private final PictureRepository pictureRepository;
    private final StationService stationService;
    private final Cloudinary cloudinary;
    private final UserService userService;
    private final StationRepository stationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, StationService stationService, Cloudinary cloudinary, UserService userService, StationRepository stationRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.stationService = stationService;
        this.cloudinary = cloudinary;
        this.userService = userService;

        this.stationRepository = stationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public List<PictureShortInfoDto> getAllPicturesByStationId(Long stationId) {
        Station station = stationService.getStationById(stationId);
        List<Picture> pictures = station.getPictures();
        List<PictureShortInfoDto> pictureShortInfoDtos = new ArrayList<>();
        for (Picture picture : pictures) {
            PictureShortInfoDto pictureShortInfoDto = modelMapper.map(picture, PictureShortInfoDto.class);
            pictureShortInfoDto.setAuthor(picture.getAuthor().getUsername());
            pictureShortInfoDtos.add(pictureShortInfoDto);
        }

        return pictureShortInfoDtos;
    }

    @Override
    public Picture getPicturebyId(Long id) {
        return pictureRepository.findById(id).orElse(null);
        //todo: throw if picture is null
    }

    @Override
    @Transactional
    public void deletePictureById(Long id) throws IOException {
        //todo
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
    public String getPictureUrlById(Long id) {
        Picture picturebyId = this.getPicturebyId(id);
        //todo: throw if picture is null
        return picturebyId.getPath();
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
        if(author.getVisitedStations().size()>50){
            author.setLevel(Level.ПЪТЕШЕСТВЕНИК);
        } else if (author.getVisitedStations().size()>10) {
            author.setLevel(Level.НАПРЕДНАЛ);
        }
        userRepository.save(author);
        this.save(picture);
        station.getPictures().add(picture);
        stationRepository.save(station);

    }

    @Override
    public List<CommentViewDto> getPictureComments(Long pictureId) {
        Picture picture = pictureRepository.findById(pictureId).orElse(null);
        //todo: throw if picture is null
        List<Comment> comments = picture.getComments();
        List<CommentViewDto> commentViewDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentViewDto commentViewDto = modelMapper.map(comment, CommentViewDto.class);
            commentViewDto.setPictureId(comment.getPicture().getId());
            commentViewDto.setAuthor(comment.getAuthor().getUsername());
            commentViewDtos.add(commentViewDto);
        }

        return commentViewDtos;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }


}
