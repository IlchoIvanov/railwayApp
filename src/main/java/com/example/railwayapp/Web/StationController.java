package com.example.railwayapp.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StationController {
    @GetMapping("st")
    public String st() {
        return "station";
    }
    @GetMapping("/stations/{id}")
    public String stations(@PathVariable long id) {

        return "station";
    }
}
