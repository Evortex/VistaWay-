package com.example.client;

import com.example.dto.Tour;
import com.example.repository.TourRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourRestTemplateClientWithRedis {

    private final TourRedisRepository redisRepository;
    private final TourFeignClient tourFeignClient;

    public Tour getTour(String tourId) {
        log.debug("In ticket-service called method getTour");
        Tour tourFromRedis = checkRedisCache(tourId);

        if (tourFromRedis != null) {
            log.debug("In ticket-service successfully retrieved an tour with id: %s from the redis cache: %s".formatted(
                    tourId, tourFromRedis));
            return tourFromRedis;
        }
        log.debug("Redis have no tour with id: %s".formatted(tourId));

        Tour tour = tourFeignClient.getTourById(tourId);
        if (tour != null) {
            cacheTourObject(tour);
        }
        return tour;
    }

    private Tour checkRedisCache(String tourId) {
        try {
            return redisRepository.findById(tourId).orElse(null);
        } catch (Exception ex) {
            log.error("Error while trying to retrieve tour with id: %s from Redis.  Exception: %s".formatted(
                    tourId, ex));
            return null;
        }
    }

    private void cacheTourObject(Tour tour) {
        try {
            redisRepository.save(tour);
        } catch (Exception ex) {
            log.error("Error while trying to cache tour with id: %s in Redis. Exception %s".formatted(tour.getId(), ex));
        }
    }
}
