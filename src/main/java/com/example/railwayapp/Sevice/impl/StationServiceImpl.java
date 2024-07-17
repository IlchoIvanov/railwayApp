package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Dto.PictureShortInfoDto;
import com.example.railwayapp.Model.Dto.StationViewDto;
import com.example.railwayapp.Model.Entity.Enum.StationType;
import com.example.railwayapp.Model.Entity.Picture;
import com.example.railwayapp.Model.Entity.Station;
import com.example.railwayapp.Repository.StationRepository;
import com.example.railwayapp.Sevice.StationService;
import com.example.railwayapp.Sevice.StationShortDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationrepository;

    public StationServiceImpl(StationRepository stationrepository) {
        this.stationrepository = stationrepository;
    }

    @Override
    public List<StationShortDto> getAllStations() {
        //TODO: modelMapper
        List<StationShortDto> stations = new ArrayList<>();
        List<Station> all = stationrepository.findAll();
        ArrayList<Station> collect = all.stream().filter(s -> s.getStationType().equals(StationType.STATION)).collect(Collectors.toCollection(ArrayList::new));
        for (Station station : collect) {
            StationShortDto stationShortDto = new StationShortDto();
            stationShortDto.setId(station.getId());
            stationShortDto.setName(station.getName());
            stations.add(stationShortDto);
        }
        return stations;

    }

    @Override
    public List<StationShortDto> getAllStops() {
        //TODO: modelMapper
        List<StationShortDto> stops = new ArrayList<>();
        List<Station> all = stationrepository.findAll();
        ArrayList<Station> collect = all.stream().filter(s -> s.getStationType().equals(StationType.STOP)).collect(Collectors.toCollection(ArrayList::new));
        for (Station station : collect) {
            StationShortDto stationShortDto = new StationShortDto();
            stationShortDto.setId(station.getId());
            stationShortDto.setName(station.getName());
            stops.add(stationShortDto);
        }
        return stops;
    }

    @Override
    public List<StationShortDto> getAllRps() {
        //TODO: modelMapper
        List<StationShortDto> rps = new ArrayList<>();
        List<Station> all = stationrepository.findAll();
        ArrayList<Station> collect = all.stream().filter(s -> s.getStationType().equals(StationType.RP)).collect(Collectors.toCollection(ArrayList::new));
        for (Station station : collect) {
            StationShortDto stationShortDto = new StationShortDto();
            stationShortDto.setId(station.getId());
            stationShortDto.setName(station.getName());
            rps.add(stationShortDto);
        }
        return rps;
    }

    @Override
    public StationViewDto getStationById(long id) {
        //TODO: modelMapper and exception
        Optional<Station> byId = stationrepository.findById(id);
        if (byId.isPresent()) {
            Station station = byId.get();
            StationViewDto stationViewDto = new StationViewDto();
            stationViewDto.setId(station.getId());
            stationViewDto.setName(station.getName());
            stationViewDto.setDescription(station.getDescription());
            stationViewDto.setStationType(station.getStationType());
            stationViewDto.setTicketsSale(station.isTicketsSale());
            List<Picture> pictures = station.getPictures();
            List<PictureShortInfoDto> pictureShortInfoDtos = new ArrayList<>();
            // TODO: modelMapper
            for (Picture picture : pictures) {
                PictureShortInfoDto dto = new PictureShortInfoDto();
                dto.setId(picture.getId());
                dto.setPath(picture.getPath());
                dto.setAuthor(picture.getAuthor().getUsername());
                pictureShortInfoDtos.add(dto);
            }
            stationViewDto.setPictures(pictureShortInfoDtos);
            return stationViewDto;

        }else{
            return null;
        }
    }
}
