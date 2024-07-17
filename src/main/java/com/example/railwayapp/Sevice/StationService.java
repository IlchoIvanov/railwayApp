package com.example.railwayapp.Sevice;

import com.example.railwayapp.Model.Dto.StationViewDto;

import java.util.List;

public interface StationService {
    List<StationShortDto> getAllStations();
    List<StationShortDto> getAllStops();
    List<StationShortDto> getAllRps();

    StationViewDto getStationById(long id);
}
