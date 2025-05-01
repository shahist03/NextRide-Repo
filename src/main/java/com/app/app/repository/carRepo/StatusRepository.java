package com.app.app.repository.carRepo;

import com.app.app.entity.cars.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}