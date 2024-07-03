package com.example.railwayapp.Web;

import com.example.railwayapp.Model.Entity.RailwayLine;
import com.example.railwayapp.Sevice.RailwayLineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LinesController {
    private final RailwayLineService railwayLineService;

    public LinesController(RailwayLineService railwayLineService) {
        this.railwayLineService = railwayLineService;
    }

    @GetMapping("/lines")
    public String lines(Model model) {
        List<RailwayLine> allRailwayLines = railwayLineService.getAllRailwayLines();
        model.addAttribute("allRailwayLines", allRailwayLines);
        return "lines";
    }

}
