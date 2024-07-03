package com.example.railwayapp.Sevice;

import com.example.railwayapp.Model.Entity.RailwayLine;

import java.util.List;


public interface RailwayLineService {


   RailwayLine findLineById(long id);

   List<RailwayLine> getAllRailwayLines();
}
