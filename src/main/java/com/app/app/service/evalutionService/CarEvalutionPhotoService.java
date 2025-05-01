package com.app.app.service.evalutionService;

import com.app.app.repository.evalutionRepo.CarEvalutionPhotosRepository;
import org.springframework.stereotype.Service;

@Service
public class CarEvalutionPhotoService {

    private CarEvalutionPhotosRepository carEvalutionPhotosRepository;

    public CarEvalutionPhotoService(CarEvalutionPhotosRepository carEvalutionPhotosRepository) {
        this.carEvalutionPhotosRepository = carEvalutionPhotosRepository;
    }


/*
    public CarImage save(CarImage image) {
        return carEvalutionPhotosRepository.save(image).getUrl();

    }*/
}
