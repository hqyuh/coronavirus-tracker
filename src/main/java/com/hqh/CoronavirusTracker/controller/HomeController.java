package com.hqh.CoronavirusTracker.controller;


import com.hqh.CoronavirusTracker.models.LocationStats;
import com.hqh.CoronavirusTracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStart = coronaVirusDataService.getAllStarts();
        int totalReport = allStart.stream().mapToInt(s -> s.getLatestTotalCases()).sum();
        int totalNewCases = allStart.stream().mapToInt(s -> s.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStart);
        model.addAttribute("totalReportedCases", totalReport);
        model.addAttribute("totalNewCase", totalNewCases);
        return "home";
    }

}
