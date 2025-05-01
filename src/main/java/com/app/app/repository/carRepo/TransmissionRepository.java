package com.app.app.repository.carRepo;

import com.app.app.entity.cars.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
}