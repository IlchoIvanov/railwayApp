package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Dto.RailwayLineSummaryDto;
import com.example.railwayapp.Model.Entity.RailwayLine;
import com.example.railwayapp.Repository.RailwayLineRepository;
import com.example.railwayapp.Sevice.PictureService;
import com.example.railwayapp.Sevice.RailwayLineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<RailwayLineSummaryDto> getAllRailwayLines() {
       return railwayLineRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }
    private RailwayLineSummaryDto mapToDto(RailwayLine railwayLine) {
        RailwayLineSummaryDto dto = new RailwayLineSummaryDto();
        dto.setNumber(railwayLine.getNumber());
        dto.setRandomPicturePath(pictureService.findRandomPicturePath(dto.getNumber()));
        return dto;
    }
}
