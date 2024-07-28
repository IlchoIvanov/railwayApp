package com.example.railwayapp.service;

import com.example.railwayapp.model.dto.StationShortDto;
import com.example.railwayapp.model.dto.StationViewDto;
import com.example.railwayapp.model.entity.Station;

import java.util.List;

public interface StationService {
    List<StationShortDto> getAllStations();
    List<StationShortDto> getAllStops();
    List<StationShortDto> getAllRps();

    StationViewDto getStationInfoById(long id);
    Station getStationById(long id);
}
