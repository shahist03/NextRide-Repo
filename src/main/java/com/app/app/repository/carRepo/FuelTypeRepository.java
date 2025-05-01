package com.app.app.repository.carRepo;

import com.app.app.entity.cars.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
}