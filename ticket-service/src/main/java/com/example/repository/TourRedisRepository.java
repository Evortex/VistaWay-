package com.example.repository;

import com.example.dto.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRedisRepository extends CrudRepository<Tour, String> {
}
