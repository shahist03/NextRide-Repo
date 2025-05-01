package com.app.app.service.carService;


import com.app.app.entity.cars.Brand;
import com.app.app.repository.carRepo.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    public Brand addBrandName(String name){
        Brand brand=new Brand();
        brand.setName(name);
        return brandRepository.save(brand);
    }
}
