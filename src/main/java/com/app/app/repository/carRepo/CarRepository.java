package com.app.app.repository.carRepo;


import com.app.app.entity.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            "SELECT c FROM Car c " +
                    "JOIN c.brand b " +
                    "JOIN c.fuelType f " +
                    "JOIN c.model m " +
                    "JOIN c.year y " +
                    "JOIN c.transmission t " +
                    "WHERE b.name = :param " +
                    "OR t.type = :param " +
                    "OR f.fuelType = :param " +
                    "OR m.name = :param " +
                    "OR CAST(y.year AS string) >= :param"
    )
    List<Car> searchCar(@Param("param") String param);



}
