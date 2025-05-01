package com.app.app.controller.evalutionController;

import com.app.app.entity.evalution.CarDetailedEvalution;
import com.app.app.service.evalutionService.CarDetailedEvalutionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/car-details")
public class CarDetailedEvalutionController {


    private CarDetailedEvalutionService carDetailedEvalutionService;

    public CarDetailedEvalutionController(CarDetailedEvalutionService carDetailedEvalutionService) {
        this.carDetailedEvalutionService = carDetailedEvalutionService;
    }

    //http://localhost:8080/api/v1/car-details/evaluation
    @PostMapping("/evaluation")
    public String getCarEvaluation(
            @RequestBody CarDetailedEvalution carDetailedEvalution
            ) {
        carDetailedEvalutionService.addCarDetailedEvalution(carDetailedEvalution);
        return "CarDetailedEvalution added successfully";
    }

}
