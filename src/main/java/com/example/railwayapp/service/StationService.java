package com.example.railwayapp.service;

import com.example.railwayapp.Model.Dto.StationShortDto;
import com.example.railwayapp.Model.Dto.StationViewDto;
import com.example.railwayapp.Model.Entity.Station;

import java.util.List;

public interface StationService {
    List<StationShortDto> getAllStations();
    List<StationShortDto> getAllStops();
    List<StationShortDto> getAllRps();

    StationViewDto getStationInfoById(long id);
    Station getStationById(long id);
}
