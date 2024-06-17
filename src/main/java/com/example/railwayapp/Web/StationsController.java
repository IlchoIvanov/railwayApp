package com.example.railwayapp.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StationsController {
    @GetMapping("/stations")
    public ModelAndView stations(){
        return new ModelAndView("stations");
    }
}
