package com.app.app.repository.carRepo;

import com.app.app.entity.cars.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}