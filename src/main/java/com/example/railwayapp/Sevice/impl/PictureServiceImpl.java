package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Entity.Picture;
import com.example.railwayapp.Repository.PictureRepository;
import com.example.railwayapp.Sevice.PictureService;
import org.springframework.stereotype.Service;


@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

}
