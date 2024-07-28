package com.example.railwayapp.web;

import com.example.railwayapp.model.dto.RailwayLineAddDto;
import com.example.railwayapp.model.dto.RailwayLineViewDto;
import com.example.railwayapp.service.RailwayLineService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LinesController {
    private final RailwayLineService railwayLineService;

    public LinesController(RailwayLineService railwayLineService) {
        this.railwayLineService = railwayLineService;
    }

    @GetMapping("/lines")
    public String lines(Model model) {
        List<RailwayLineViewDto> allRailwayLines = railwayLineService.getAllRailwayLines();
        model.addAttribute("allRailwayLines", allRailwayLines);
        return "lines";
    }
    @GetMapping("/lines/{id}")
    public String getLine(@PathVariable long id, Model model) {
        RailwayLineViewDto line = railwayLineService.findLineById(id);

        model.addAttribute("line", line);

        return "line";
    }
    @GetMapping("/lines/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addLineView() {
        return "add-line";
    }
    @ModelAttribute("lineData")
    public RailwayLineAddDto commentAddDto() {
        return new RailwayLineAddDto();
    }
    @PostMapping("/lines/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String addLine(@Valid
    RailwayLineAddDto lineData, BindingResult result, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("add-line");
        modelAndView.addObject("lineData", lineData);
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("lineData", lineData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.lineData", result);
            return "redirect:/lines/add";
        }
        railwayLineService.addLine(lineData);
        return "redirect:/lines";
    }

    @DeleteMapping("/delete/line/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteLine(@PathVariable long id) {
        railwayLineService.deleteLineById(id);
        return "redirect:/lines";

    }

}
