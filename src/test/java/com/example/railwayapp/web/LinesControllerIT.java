package com.example.railwayapp.web;



import com.example.railwayapp.model.dto.RailwayLineViewDto;
import com.example.railwayapp.service.RailwayLineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
class LinesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RailwayLineService railwayLineService;

    @InjectMocks
    private LinesController linesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLines() throws Exception {
        // Arrange
        RailwayLineViewDto line1 = new RailwayLineViewDto(1L, "Line 1");
        RailwayLineViewDto line2 = new RailwayLineViewDto(2L, "Line 2");
        when(railwayLineService.getAllRailwayLines()).thenReturn(Arrays.asList(line1, line2));

        // Act & Assert
        mockMvc.perform(get("/lines"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allRailwayLines"))
                .andExpect(view().name("lines"));
    }

    @Test
    void testGetLine() throws Exception {
        // Arrange
        long lineId = 1L;
        RailwayLineViewDto line = new RailwayLineViewDto(lineId, "Line 1");
        when(railwayLineService.findLineById(lineId)).thenReturn(line);

        // Act & Assert
        mockMvc.perform(get("/lines/{id}", lineId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("line"))
                .andExpect(view().name("line"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAddLineView() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/lines/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-line"));
    }






}
