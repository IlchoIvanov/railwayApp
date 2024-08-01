package com.example.railwayapp.service.impl;

import com.example.railwayapp.model.dto.PictureShortInfoDto;
import com.example.railwayapp.model.dto.StationViewDto;
import com.example.railwayapp.model.entity.Enum.StationType;
import com.example.railwayapp.model.entity.Picture;
import com.example.railwayapp.model.entity.Station;
import com.example.railwayapp.repository.StationRepository;
import com.example.railwayapp.service.StationService;
import com.example.railwayapp.model.dto.StationShortDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationrepository;
    private final ModelMapper modelmapper;

    public StationServiceImpl(StationRepository stationrepository, ModelMapper modelmapper) {
        this.stationrepository = stationrepository;
        this.modelmapper = modelmapper;
    }

    @Override
    public List<StationShortDto> getAllStations() {

        List<StationShortDto> stations = new ArrayList<>();
        ArrayList<Station> allStations = stationrepository.findAll().stream().filter(s -> s.getStationType().equals(StationType.STATION)).collect(Collectors.toCollection(ArrayList::new));
        allStations.forEach(s -> stations.add(modelmapper.map(s, StationShortDto.class)));
        return stations;

    }

    @Override
    public List<StationShortDto> getAllStops() {

        List<StationShortDto> stops = new ArrayList<>();
        ArrayList<Station> allStations = stationrepository.findAll().stream().filter(s -> s.getStationType().equals(StationType.STOP)).collect(Collectors.toCollection(ArrayList::new));
        allStations.forEach(s -> stops.add(modelmapper.map(s, StationShortDto.class)));
        return stops;
    }

    @Override
    public List<StationShortDto> getAllRps() {
        List<StationShortDto> rps = new ArrayList<>();
        ArrayList<Station> allStations = stationrepository.findAll().stream().filter(s -> s.getStationType().equals(StationType.RP)).collect(Collectors.toCollection(ArrayList::new));
        allStations.forEach(s -> rps.add(modelmapper.map(s, StationShortDto.class)));
        return rps;
    }

    @Override
    public StationViewDto getStationInfoById(long id) {
        Optional<Station> byId = stationrepository.findById(id);
        if (byId.isPresent()) {
            Station station = byId.get();
            StationViewDto stationViewDto = modelmapper.map(station, StationViewDto.class);
            List<Picture> pictures = station.getPictures();
            List<PictureShortInfoDto> pictureShortInfoDtos = new ArrayList<>();
            for (Picture picture : pictures) {
                PictureShortInfoDto dto = modelmapper.map(picture, PictureShortInfoDto.class);
                dto.setAuthor(picture.getAuthor().getUsername());
                pictureShortInfoDtos.add(dto);
            }
            stationViewDto.setPictures(pictureShortInfoDtos);
            return stationViewDto;

        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public Station getStationById(long id) {
        return stationrepository.getById(id);
    }
}
