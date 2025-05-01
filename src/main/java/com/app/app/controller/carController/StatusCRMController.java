package com.app.app.controller.carController;


import com.app.app.entity.cars.Car;
import com.app.app.entity.cars.Status;
import com.app.app.entity.evalution.Agents;
import com.app.app.entity.evalution.CustomerVisit;
import com.app.app.repository.carRepo.CarRepository;
import com.app.app.repository.carRepo.StatusRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/update-status")
public class StatusCRMController {

    private CarRepository carRepository;
    private StatusRepository statusRepository;

    public StatusCRMController(CarRepository carRepository, StatusRepository statusRepository) {
        this.carRepository = carRepository;
        this.statusRepository = statusRepository;
    }

    //http://localhost:8080/api/v1/update-status/status-update?carId={carId}&statusId={statusId}
    @PutMapping("/status-update")
    public String allocateAgent(
            @RequestParam long carId,
            @RequestParam long statusId
    ) {
        Optional<Car> opCar = carRepository.findById(carId);
        if (!opCar.isPresent()) {
            return "Car with ID " + carId + " not found.";
        }

        Optional<Status> opStatus = statusRepository.findById(statusId);
        if (!opStatus.isPresent()) {
            return "Status with ID " + statusId + " not found.";
        }

        Car car = opCar.get();
        car.setStatus(opStatus.get());
        carRepository.save(car);

        return "Status updated successfully";
    }

}
