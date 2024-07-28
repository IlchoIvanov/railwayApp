package com.example.railwayapp.Web;

import com.example.railwayapp.Model.Dto.StationViewDto;
import com.example.railwayapp.Model.User.RailwayAppUserDetails;
import com.example.railwayapp.service.StationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/stations/{id}")
    public String stations(@PathVariable long id, @AuthenticationPrincipal RailwayAppUserDetails userDetails, Model model) {
        if(userDetails == null) {
            StationViewDto viewDto = stationService.getStationInfoById(id);
            model.addAttribute("station", viewDto);
            return "station";
        }
        StationViewDto viewDto = stationService.getStationInfoById(id);
        model.addAttribute("station", viewDto);
        model.addAttribute("user", userDetails.getName());
        return "station";
    }

}
