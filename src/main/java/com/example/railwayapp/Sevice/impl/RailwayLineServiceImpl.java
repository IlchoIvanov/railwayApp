package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Dto.RailwayLineViewDto;
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
    public RailwayLineViewDto findLineById(long id) {
        RailwayLine railwayLine = railwayLineRepository.findById(id).orElse(null);
        if(railwayLine == null){
            return null;
            //TODO: throw exception
        }
        RailwayLineViewDto dto = new RailwayLineViewDto();
        dto.setId(railwayLine.getId());
        dto.setDescription(railwayLine.getDescription());
        dto.setLength(railwayLine.getLength());
        dto.setNumber(railwayLine.getNumber());
        dto.setRoute(railwayLine.getRoute());
        return dto;
    }

    @Override
    public List<RailwayLineViewDto> getAllRailwayLines() {
        List<RailwayLine> all = railwayLineRepository.findAll();
        List<RailwayLineViewDto> dtos = new ArrayList<>();
        for (RailwayLine railwayLine : all) {
            RailwayLineViewDto dto = new RailwayLineViewDto();
            dto.setId(railwayLine.getId());
            dto.setDescription(railwayLine.getDescription());
            dto.setLength(railwayLine.getLength());
            dto.setNumber(railwayLine.getNumber());
            dto.setRoute(railwayLine.getRoute());
            dtos.add(dto);
        }
        return dtos;
    }
}
