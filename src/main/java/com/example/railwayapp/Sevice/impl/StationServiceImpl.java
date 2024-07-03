package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Entity.Enum.StationType;
import com.example.railwayapp.Repository.StationRepository;
import com.example.railwayapp.Sevice.StationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationrepository;

    public StationServiceImpl(StationRepository stationrepository) {
        this.stationrepository = stationrepository;
    }

    @Override
    public List<String> getAllStationNames() {
        return stationrepository.findAllStationNames(StationType.STATION);

    }

    @Override
    public List<String> getAllStopNames() {
        return stationrepository.findAllStationNames(StationType.STOP);
    }
}
