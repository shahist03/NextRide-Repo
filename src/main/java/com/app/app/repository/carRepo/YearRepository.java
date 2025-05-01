package com.app.app.repository.carRepo;

import com.app.app.entity.cars.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {
}