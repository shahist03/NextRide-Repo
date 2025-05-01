package com.app.app.service.carService;



import com.app.app.entity.cars.*;
import com.app.app.repository.carRepo.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class carService {

    private CarRepository carRepository;
    private BrandRepository brandRepository;
    private FuelTypeRepository fuelTypeRepository;
    private ModelRepository modelRepository;
    private TransmissionRepository TransmissionRepository;
    private YearRepository yearRepository;


    public carService(CarRepository carRepository,
                      BrandRepository brandRepository,
                      FuelTypeRepository fuelTypeRepository,
                      ModelRepository modelRepository,
                      TransmissionRepository transmissionRepository,
                      YearRepository yearRepository) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.modelRepository = modelRepository;
        this.TransmissionRepository = transmissionRepository;
        this.yearRepository = yearRepository;

    }

    public Car addCarDetails(Long brandId, Long fuelTypeId, Long modelId, Long transmissionId, Long yearId){
        Car car = new Car();
        Optional<Brand> opBrand = brandRepository.findById(brandId);
        Optional<Model> opModel = modelRepository.findById(modelId);
        Optional<FuelType> opFuelType = fuelTypeRepository.findById(fuelTypeId);
        Optional<Transmission> opTransmission = TransmissionRepository.findById(transmissionId);
        Optional<Year> opYear = yearRepository.findById(yearId);


        if(opBrand.isPresent() && opModel.isPresent() && opFuelType.isPresent() && opTransmission.isPresent() && opYear.isPresent())
        {
            car.setBrand(opBrand.get());
            car.setFuelType(opFuelType.get());
            car.setModel(opModel.get());
            car.setTransmission(opTransmission.get());
            car.setYear(opYear.get());

            return carRepository.save(car);
        }
        else {
            throw  new RuntimeException("ID's are not invalid");
        }

    }
}
