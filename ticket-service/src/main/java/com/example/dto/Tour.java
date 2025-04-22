package com.example.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("tour")
public class Tour implements Serializable {
        @Id
        private String id;
        private String tourName;
        private String contactName;
        private String contactPhone;
}
