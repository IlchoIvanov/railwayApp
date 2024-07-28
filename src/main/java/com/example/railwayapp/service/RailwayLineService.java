package com.example.railwayapp.service;

import com.example.railwayapp.model.dto.RailwayLineAddDto;
import com.example.railwayapp.model.dto.RailwayLineViewDto;

import java.util.List;


public interface RailwayLineService {


   RailwayLineViewDto findLineById(long id);

   List<RailwayLineViewDto> getAllRailwayLines();

    void addLine(RailwayLineAddDto lineData);

    void deleteLineById(long id);
}
