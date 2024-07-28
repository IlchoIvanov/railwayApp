package com.example.railwayapp.web;

import com.example.railwayapp.model.dto.StationShortDto;
import com.example.railwayapp.model.dto.StationViewDto;
import com.example.railwayapp.service.StationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StationsControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StationService stationService;

    private StationViewDto stationViewDto;
    private StationShortDto stationShortDto;

    @BeforeEach
    void setUp() {
        stationViewDto = new StationViewDto();
        stationViewDto.setId(1L);
        stationViewDto.setName("Test Station");
       List<StationViewDto> stations = Collections.singletonList(stationViewDto);
       stationShortDto = new StationShortDto();
       stationShortDto.setId(2L);
       stationShortDto.setName("Test Station");
    }

    @Test
    void testGetAllStations() throws Exception {
        when(stationService.getAllStations()).thenReturn(Collections.singletonList(stationShortDto));

        mockMvc.perform(get("/stations"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stations"))
                .andExpect(model().attribute("stations", hasSize(1)))
                .andExpect(model().attribute("stations", Collections.singletonList(stationShortDto)));
    }

    @Test
    void testGetAllStops() throws Exception {
        when(stationService.getAllStops()).thenReturn(Collections.singletonList(stationShortDto));

        mockMvc.perform(get("/stops"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stations"))
                .andExpect(model().attribute("stations", hasSize(1)))
                .andExpect(model().attribute("stations", Collections.singletonList(stationShortDto)));
    }

    @Test
    void testGetAllRps() throws Exception {
        when(stationService.getAllRps()).thenReturn(Collections.singletonList(stationShortDto));

        mockMvc.perform(get("/rps"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stations"))
                .andExpect(model().attribute("stations", hasSize(1)))
                .andExpect(model().attribute("stations", Collections.singletonList(stationShortDto)));
    }


}