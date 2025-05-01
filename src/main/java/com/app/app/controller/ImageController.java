package com.app.app.controller;

/*import com.app.app.entity.cars.CarImage;
import com.app.app.entity.cars.Car;*/

import com.app.app.entity.cars.CarImage;
import com.app.app.entity.cars.Car;
import com.app.app.repository.carRepo.CarImageRepository;
import com.app.app.repository.carRepo.CarRepository;
import com.app.app.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final BucketService bucketService;
    private CarRepository carRepository;
    private CarImageRepository carImageRepository;
    public ImageController(BucketService bucketService, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.bucketService = bucketService;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    @PostMapping("/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<CarImage> uploadCarPhotos(
            @RequestParam("file") MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable String carId
    ) {

        // Generate unique file name using carId
        String fileName = "cars/" + carId + "/" + file.getOriginalFilename();

        // Upload file
        String url = bucketService.uploadFile(file, bucketName, fileName);/*
        ImageDto imageDto=new ImageDto();
        imageDto.setImageUrl(url);*/

        Car car= carRepository.findById(Long.valueOf(carId)).get();
        CarImage image=new CarImage();
        image.setUrl(url);
        image.setCar(car);
        CarImage saved = carImageRepository.save(image);
        return new ResponseEntity<>(image, HttpStatus.OK);

    }
}
