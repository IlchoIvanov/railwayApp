package com.example.railwayapp.service;

import com.example.railwayapp.model.dto.StationShortDto;
import com.example.railwayapp.model.dto.StationViewDto;
import com.example.railwayapp.model.entity.Enum.StationType;
import com.example.railwayapp.model.entity.Picture;
import com.example.railwayapp.model.entity.Station;
import com.example.railwayapp.repository.StationRepository;
import com.example.railwayapp.service.impl.StationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StationServiceImplTest {

        @Mock
        private StationRepository stationRepository;

        @Mock
        private ModelMapper modelMapper;

        @InjectMocks
        private StationServiceImpl stationService;

        private Station station;
        private StationShortDto stationShortDto;
        private StationViewDto stationViewDto;
        private Picture picture;

        @BeforeEach
        void setUp() {
            station = new Station();
            station.setId(1L);
            station.setStationType(StationType.STATION);

            stationShortDto = new StationShortDto();
            stationShortDto.setId(1L);

            picture = new Picture();
            picture.setId(1L);

            stationViewDto = new StationViewDto();
            stationViewDto.setId(1L);
        }

        @Test
        void getAllStations() {
            when(stationRepository.findAll()).thenReturn(Arrays.asList(station));
            when(modelMapper.map(station, StationShortDto.class)).thenReturn(stationShortDto);

            List<StationShortDto> result = stationService.getAllStations();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(stationShortDto, result.get(0));
            verify(stationRepository, times(1)).findAll();
        }

        @Test
        void getAllStops() {
            station.setStationType(StationType.STOP);
            when(stationRepository.findAll()).thenReturn(Arrays.asList(station));
            when(modelMapper.map(station, StationShortDto.class)).thenReturn(stationShortDto);

            List<StationShortDto> result = stationService.getAllStops();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(stationShortDto, result.get(0));
            verify(stationRepository, times(1)).findAll();
        }

        @Test
        void getAllRps() {
            station.setStationType(StationType.RP);
            when(stationRepository.findAll()).thenReturn(Arrays.asList(station));
            when(modelMapper.map(station, StationShortDto.class)).thenReturn(stationShortDto);

            List<StationShortDto> result = stationService.getAllRps();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(stationShortDto, result.get(0));
            verify(stationRepository, times(1)).findAll();
        }

        @Test
        void getStationInfoById() {
            when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
            when(modelMapper.map(station, StationViewDto.class)).thenReturn(stationViewDto);

            StationViewDto result = stationService.getStationInfoById(1L);

            assertNotNull(result);
            assertEquals(stationViewDto, result);
            verify(stationRepository, times(1)).findById(1L);
        }

        @Test
        void getStationInfoById_notFound() {
            when(stationRepository.findById(1L)).thenReturn(Optional.empty());

            StationViewDto result = stationService.getStationInfoById(1L);

            assertNull(result);
            verify(stationRepository, times(1)).findById(1L);
        }

        @Test
        void getStationById() {
            when(stationRepository.getById(1L)).thenReturn(station);

            Station result = stationService.getStationById(1L);

            assertNotNull(result);
            assertEquals(station, result);
            verify(stationRepository, times(1)).getById(1L);
        }
    }

