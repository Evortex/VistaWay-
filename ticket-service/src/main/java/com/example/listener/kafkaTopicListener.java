package com.example.listener;

import com.example.model.Tour;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class kafkaTopicListener {

    private static final String TOPIC_CREATE_TOUR = "${topic.send-tour}";
    private static final String KAFKA_CONSUMER_GROUP_ID = "${spring.kafka.consumer.group-id}";

    @KafkaListener(
            topics = TOPIC_CREATE_TOUR,
            groupId = KAFKA_CONSUMER_GROUP_ID,
            properties = {"spring.json.value.default.type=com.aston.intensive.ticketservice.model.Tour"}
    )
    public void createTour(Tour tour) {
        log.info("Ticket-service received tour from Kafka {}", tour);

        // TODO save info to DB
    }
}
