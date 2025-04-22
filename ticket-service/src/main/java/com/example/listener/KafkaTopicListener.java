package com.example.listener;

import com.example.model.Tour;
import com.example.repository.TourRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaTopicListener {

    private final TourRedisRepository tourRedisRepository;

    @KafkaListener(
            topics = "#{'${topic.send-tour}'}",
            groupId = "#{'${spring.kafka.consumer.group-id}'}",
            properties = {
                    "spring.json.value.default.type=com.example.model.Tour"
            }
    )
    public void createTour(Tour kafkaTour) {
        log.info("✅ Ticket-service received tour from Kafka: {}", kafkaTour);

        com.example.dto.Tour redisTour = mapToRedisTour(kafkaTour);
        tourRedisRepository.save(redisTour);

        log.info("💾 Tour saved to Redis: {}", redisTour);
    }

    private com.example.dto.Tour mapToRedisTour(Tour kafkaTour) {
        return new com.example.dto.Tour(
                UUID.randomUUID().toString(),
                kafkaTour.getTourName(),
                "Default Contact",
                "000-000-0000"
        );
    }
}
