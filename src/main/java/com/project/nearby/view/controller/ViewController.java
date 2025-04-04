package com.project.nearby.view.controller;

import com.project.nearby.api.dto.CategorySearchDto;
import com.project.nearby.recommendation.service.PlaceRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PlaceRecommendationService placeRecommendationService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String address, @RequestParam String category, @RequestParam Integer radius, ModelMap map) {
        List<CategorySearchDto> results = placeRecommendationService.searchPlaces(address, category, radius);

        map.addAttribute("results", results);

        return "result";
    }
}
