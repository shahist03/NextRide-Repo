package com.app.app.controller.carController;



import com.app.app.entity.cars.Car;

import com.app.app.repository.carRepo.BrandRepository;
import com.app.app.repository.carRepo.CarRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search-car")
public class SearchCarController {

    private BrandRepository brandRepository;
    private CarRepository carRepository;

    public SearchCarController(BrandRepository brandRepository, CarRepository carRepository) {
        this.brandRepository = brandRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> searchCars(@RequestParam String param) {
        return carRepository.searchCar(param);
    }


}
