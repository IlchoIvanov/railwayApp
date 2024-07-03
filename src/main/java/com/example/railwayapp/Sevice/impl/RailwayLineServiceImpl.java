package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Entity.RailwayLine;
import com.example.railwayapp.Repository.RailwayLineRepository;
import com.example.railwayapp.Sevice.PictureService;
import com.example.railwayapp.Sevice.RailwayLineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RailwayLineServiceImpl implements RailwayLineService {
    private final RailwayLineRepository railwayLineRepository;
    private final PictureService pictureService;

    public RailwayLineServiceImpl(RailwayLineRepository railwayLineRepository, PictureService pictureService) {
        this.railwayLineRepository = railwayLineRepository;
        this.pictureService = pictureService;
    }


    @Override
    public RailwayLine findLineById(long id) {
        return railwayLineRepository.findById(id).orElse(null);
    }

    @Override
    public List<RailwayLine> getAllRailwayLines() {
        return railwayLineRepository.findAll();
    }
}
