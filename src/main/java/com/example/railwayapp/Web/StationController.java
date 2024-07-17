package com.example.railwayapp.Web;

import com.example.railwayapp.Sevice.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/stations/{id}")
    public String stations(@PathVariable long id) {

        return "station";
    }
}
