package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Entity.Picture;
import com.example.railwayapp.Repository.PictureRepository;
import com.example.railwayapp.Sevice.PictureService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String findRandomPicturePath(int railwayNumber) {
        List<Picture> picturesByLine = pictureRepository.findAllByRailwayLine_Number(railwayNumber);
        if (picturesByLine == null || picturesByLine.isEmpty()) {
            return "";
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(picturesByLine.size() + 1);
        return picturesByLine.get(randomIndex).getPath();
    }
}
