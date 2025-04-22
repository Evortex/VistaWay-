package com.example.service.impl;

import com.example.client.TourFeignClient;
import com.example.client.TourRestTemplateClientWithRedis;
import com.example.dto.TicketResponseDto;
import com.example.dto.Tour;
import com.example.service.TicketService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TourFeignClient tourFeignClient;
    private final TourRestTemplateClientWithRedis clientWithRedis;

    @CircuitBreaker(name = "findTourById", fallbackMethod = "buildFallbackTourDto")
    @Retry(name = "retryFindTourById", fallbackMethod = "buildFallbackTourDto")
    @Bulkhead(name = "bulkheadFindTourById", fallbackMethod = "buildFallbackTourDto")
    public TicketResponseDto getAvailableTickets(String tourId){
        Tour tour = tourFeignClient.getTourById(tourId);

        List<String> tickets = List.of("Ticket1", "Ticket2", "Ticket3");

        return TicketResponseDto.builder()
                .id(tourId)
                .tourName(tour.getTourName())
                .contactName(tour.getContactName())
                .contactPhone(tour.getContactPhone())
                .tickets(tickets)
                .isResponseValid(true)
                .message("recommendations can be here")
                .build();
    }

    @SuppressWarnings("unused")
    public TicketResponseDto buildFallbackTourDto(String tourId, Exception t) {
        log.error("Fallback method worked. TourDto is not received", t);

        return TicketResponseDto.builder()
                .contactName("support service")
                .contactPhone("8-800-00-00-00")
                .isResponseValid(false)
                .message("sorry, but service is not available now")
                .build();
    }

    public TicketResponseDto getAvailableTicketsWithRedis(String tourId){
        Tour tour = clientWithRedis.getTour(tourId);

        List<String> tickets = List.of("Ticket1", "Ticket2", "Ticket3");

        return TicketResponseDto.builder()
                .id(tourId)
                .tourName(tour.getTourName())
                .contactName(tour.getContactName())
                .contactPhone(tour.getContactPhone())
                .tickets(tickets)
                .isResponseValid(true)
                .message("recommendations can be here")
                .build();
    }
}
