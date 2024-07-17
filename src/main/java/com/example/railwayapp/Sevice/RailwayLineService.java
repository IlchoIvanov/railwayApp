package com.example.railwayapp.Sevice;

import com.example.railwayapp.Model.Dto.RailwayLineViewDto;
import com.example.railwayapp.Model.Entity.RailwayLine;

import java.util.List;


public interface RailwayLineService {


   RailwayLineViewDto findLineById(long id);

   List<RailwayLineViewDto> getAllRailwayLines();
}
