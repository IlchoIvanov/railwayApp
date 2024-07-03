package com.example.railwayapp.Web;

import com.example.railwayapp.Model.Entity.RailwayLine;
import com.example.railwayapp.Sevice.PictureService;
import com.example.railwayapp.Sevice.RailwayLineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LineController {

    private final RailwayLineService railwayLineService;


    public LineController(RailwayLineService railwayLineService, PictureService pictureService) {
        this.railwayLineService = railwayLineService;
    }

    @GetMapping("/lines/{id}")
    public String getLine(@PathVariable long id, Model model) {
         RailwayLine line = railwayLineService.findLineById(id);
         if (line == null) {
             return "redirect:/";
         }
         model.addAttribute("line", line);

        return "line";
    }
}
