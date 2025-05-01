package com.app.app.repository.carRepo;

import com.app.app.entity.cars.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String brand);
}