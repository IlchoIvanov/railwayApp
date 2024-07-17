package com.example.railwayapp.Web;

import com.example.railwayapp.Sevice.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StationsController {
    private final StationService stationService;

    public StationsController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/stations")
    public String stations(Model model){
        model.addAttribute("stations", stationService.getAllStations());
        return "stations";
    }
    @GetMapping("/stops")
    public String stops(Model model){
        model.addAttribute("stations", stationService.getAllStops());
        return "stations";
    }
    @GetMapping("/rps")
    public String rps(Model model){
        model.addAttribute("stations", stationService.getAllRps());
        return "stations";
    }

}
