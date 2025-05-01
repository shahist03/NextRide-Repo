package com.app.app.repository.carRepo;

import com.app.app.entity.cars.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}