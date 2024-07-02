package com.example.railwayapp.Sevice;

import com.example.railwayapp.Model.Dto.RailwayLineViewDto;
import com.example.railwayapp.Model.Entity.RailwayLine;


import java.util.List;

public interface RailwayLineService {
   List<RailwayLineViewDto> getAllRailwayLines();

   RailwayLine findLineById(long id);
}
