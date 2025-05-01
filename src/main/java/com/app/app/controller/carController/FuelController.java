package com.app.app.controller.carController;


import com.app.app.service.carService.FuelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fuel")
public class FuelController {
    //http://localhost:8080/api/v1/fuel
    private FuelService FuelService;

    public FuelController(FuelService fuelService) {
        this.FuelService= fuelService;
    }


    @PostMapping("/add-fuel")
    public String addFuel(
            @RequestParam String fuelType
    ) {
        FuelService.addFuelDetails(fuelType);
        return "Fuel added successfully";
    }
}
