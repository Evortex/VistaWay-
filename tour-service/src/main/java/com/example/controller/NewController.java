package com.example.controller;

import com.example.dto.TourDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class NewController {

    @GetMapping("/name")
    public String name() {
        return "New Controller";
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<TourDto> getTour(@PathVariable("tourId") String tourId) {

        return ResponseEntity.ok(TourDto.builder()
                .id(tourId)
                .tourName("VisitCastle")
                .contactName("Nikolay")
                .contactPhone("11111111111")
                .build());
    }
}
