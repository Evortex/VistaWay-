package com.example.service;

import com.example.dto.TicketResponseDto;

public interface TicketService {

    TicketResponseDto getAvailableTickets(String tourId);
}
