package com.example.client;

import com.example.dto.Tour;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tour-service")
public interface TourFeignClient {

    @GetMapping(value = "/new/tour/{tourId}", consumes = "application/json")
    Tour getTourById(@PathVariable("tourId") String tourId);
}
