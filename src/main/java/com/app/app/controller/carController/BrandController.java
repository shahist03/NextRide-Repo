package com.app.app.controller.carController;

import com.app.app.service.carService.BrandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    // http://localhost:8080/api/v1/brands
    private BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add-brand")
    public String addBrand(
            @RequestParam String name
    ){
        brandService.addBrandName(name);
        return "Brand added successfully";

    }

}
