package com.example.controller;

import com.example.dao.LocationDao;
import com.example.entity.Location;
import com.example.model.LocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class LocationController {

    @Autowired
    LocationDao locationDao;

    @GetMapping("/locations")
    public String getLocations(Model model) {
        List<Location> locations = locationDao.locations();
        model.addAttribute("locations", locations);
        return "/location/locations";
    }

    @GetMapping("/addLocation")
    public String addLocation(Model model) {
        LocationModel locationModel = new LocationModel();
        model.addAttribute("location", locationModel);
        return "/location/addLocation";
    }

    @PostMapping("/saveLocation")
    public String saveLocation(@ModelAttribute("location") LocationModel locationModel, Model model) {
        locationDao.save(locationModel);
        return getLocations(model);
    }
}
