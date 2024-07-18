package com.example.railwayapp.Web;

import com.example.railwayapp.Model.Dto.StationViewDto;
import com.example.railwayapp.Model.User.RailwayAppUserDetails;
import com.example.railwayapp.Sevice.StationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/stations/{id}")
    public String stations(@PathVariable long id,  @AuthenticationPrincipal RailwayAppUserDetails userDetails, Model model) {
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
