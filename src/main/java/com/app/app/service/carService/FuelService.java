package com.app.app.service.carService;

import com.app.app.entity.cars.FuelType;
import com.app.app.repository.carRepo.FuelTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class FuelService {
    private FuelTypeRepository fuelTypeRepository;
    // constructor injection
    public FuelService(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }
    public FuelType addFuelDetails(String fuelType) {
        FuelType fuel=new FuelType();
        fuel.setFuelType(fuelType);
        return fuelTypeRepository.save(fuel);
    }
}
