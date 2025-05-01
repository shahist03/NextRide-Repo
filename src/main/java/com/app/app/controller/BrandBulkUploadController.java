package com.app.app.controller;


import com.app.app.entity.cars.Brand;
import com.app.app.repository.carRepo.BrandRepository;
import com.app.app.service.BulkUploadBrandNameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/apt/v1/brand/bulk-upload")
public class BrandBulkUploadController {

    private BulkUploadBrandNameService bulkUploadBrandNameService;

    private BrandRepository brandRepository;


    public BrandBulkUploadController(BulkUploadBrandNameService bulkUploadBrandNameService, BrandRepository brandRepository) {
        this.bulkUploadBrandNameService = bulkUploadBrandNameService;
        this.brandRepository = brandRepository;
    }
    //http://localhost:8080/apt/v1/brand/bulk-upload/upload
    @PostMapping("/upload")
    public String uploadExcelFile(
            @RequestParam("filepath") String filepath
    ){
        try{
            List<Brand> brands = bulkUploadBrandNameService.readExcel(filepath);
            brandRepository.saveAll(brands);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return "Brand bulk upload success";
    }
}
