package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TicketResponseDto(
        String id,
        String tourName,
        String contactName,
        String contactPhone,
        boolean isResponseValid,
        String message,
        List<String> tickets
) {
}
