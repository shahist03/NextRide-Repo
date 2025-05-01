package com.app.app.controller.evalutionController;

import com.app.app.entity.evalution.CarDetailedEvalution;
import com.app.app.entity.evalution.CarEvalutionPhotos;
import com.app.app.repository.evalutionRepo.CarDetailedEvalutionRepository;
import com.app.app.repository.evalutionRepo.CarEvalutionPhotosRepository;
import com.app.app.service.evalutionService.MultipleBucketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/evalution/car-actual-photo")
public class CarActualPhotoController {

    private final CarEvalutionPhotosRepository carEvalutionPhotosRepository;
    private final MultipleBucketService multipleBucketService;
    private CarDetailedEvalutionRepository carDetailedEvalutionRepository;

    public CarActualPhotoController(CarEvalutionPhotosRepository carEvalutionPhotosRepository,
                                    MultipleBucketService multipleBucketService, CarDetailedEvalutionRepository carDetailedEvalutionRepository) {
        this.carEvalutionPhotosRepository = carEvalutionPhotosRepository;
        this.multipleBucketService = multipleBucketService;
        this.carDetailedEvalutionRepository = carDetailedEvalutionRepository;
    }

    @PostMapping("/upload/files/{bucketName}/car/{carDetailedEvalutionId}")
    public ResponseEntity<String> uploadCarPhotos(
            @RequestParam("files") MultipartFile[] files,
            @PathVariable String bucketName,
            @PathVariable Long carDetailedEvalutionId
    ) {
        // Upload files and get their URLs
        List<String> urls = multipleBucketService.uploadFiles(files, bucketName, "cars/" + carDetailedEvalutionId);

        // Fetch CarDetailedEvalution entity from DB
        CarDetailedEvalution carDetailedEvalution = carDetailedEvalutionRepository.findById(carDetailedEvalutionId)
                .orElseThrow(() -> new RuntimeException("Car ID not found!"));

        // Store images in database
        List<CarEvalutionPhotos> images = new ArrayList<>();
        for (String url : urls) {
            CarEvalutionPhotos image = new CarEvalutionPhotos();
            image.setPhotoUrl(carDetailedEvalutionId + "/" + url);
            image.setCarDetailedEvalution(carDetailedEvalution);
            images.add(carEvalutionPhotosRepository.save(image));
        }

        return ResponseEntity.ok("done");  // ✅ Returns String with HTTP 200 status
    }



}
