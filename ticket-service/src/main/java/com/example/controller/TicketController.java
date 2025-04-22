package com.example.controller;

import com.example.dto.TicketResponseDto;
import com.example.service.impl.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceImpl ticketServiceImpl;

    @GetMapping("/availableTickets/{tourId}")
    public ResponseEntity<TicketResponseDto> getAvailableTickets(@PathVariable("tourId") String tourId) {
        TicketResponseDto ticketResponseDto = ticketServiceImpl.getAvailableTickets(tourId);
        return ResponseEntity.ok(ticketResponseDto);
    }

    @GetMapping("/availableTicketsWithRedis/{tourId}")
    public ResponseEntity<TicketResponseDto> getAvailableTicketsWithRedis(@PathVariable("tourId") String tourId) {
        TicketResponseDto ticketResponseDto = ticketServiceImpl.getAvailableTicketsWithRedis(tourId);
        return ResponseEntity.ok(ticketResponseDto);
    }
}
