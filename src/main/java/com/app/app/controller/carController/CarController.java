package com.app.app.controller.carController;


import com.app.app.service.carService.carService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private carService carService;

    public CarController(carService carService) {
        this.carService = carService;
    }

    @PostMapping("/add-car")
    public String addCar(
            @RequestParam Long brandId,
            @RequestParam Long fuelTypeId,
            @RequestParam Long modelId,
            @RequestParam Long transmissionId,
            @RequestParam Long yearId

    ) {
         // Added method call to add car to the database using car_service
        carService.addCarDetails(brandId, fuelTypeId, modelId, transmissionId, yearId);
        return "Car added successfully";
    }
}
