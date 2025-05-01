package com.app.app.service.evalutionService;



import com.app.app.entity.evalution.CarDetailedEvalution;
import com.app.app.repository.evalutionRepo.CarDetailedEvalutionRepository;

import org.springframework.stereotype.Service;


@Service
public class CarDetailedEvalutionService {


    private CarDetailedEvalutionRepository carDetailedEvalutionRepository;  // ✅ Inject repository

    public CarDetailedEvalutionService(CarDetailedEvalutionRepository carDetailedEvalutionRepository) {
        this.carDetailedEvalutionRepository = carDetailedEvalutionRepository;
    }

    public CarDetailedEvalution addCarDetailedEvalution(CarDetailedEvalution detailedEvalution) {
        return carDetailedEvalutionRepository.save(detailedEvalution);
    }
}
