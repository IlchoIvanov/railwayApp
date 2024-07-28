package com.example.railwayapp.service;

import com.example.railwayapp.Model.Dto.RailwayLineAddDto;
import com.example.railwayapp.Model.Dto.RailwayLineViewDto;

import java.util.List;


public interface RailwayLineService {


   RailwayLineViewDto findLineById(long id);

   List<RailwayLineViewDto> getAllRailwayLines();

    void addLine(RailwayLineAddDto lineData);

    void deleteLineById(long id);
}
