package com.example.service;

import com.example.model.Tour;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourService {

    @Value("${topic.send-tour}")
    private String sendClientTopic;

    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendTourEvent(Tour tour) {

        log.info("tour service started");

        kafkaTemplate.send(sendClientTopic, tour);
    }
}
