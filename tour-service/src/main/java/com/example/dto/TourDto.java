package com.example.dto;

import lombok.Builder;

@Builder
public record TourDto(
        String id,
        String tourName,
        String contactName,
        String contactPhone
) {
}
