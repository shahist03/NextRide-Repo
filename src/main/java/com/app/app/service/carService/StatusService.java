package com.app.app.service.carService;

import com.app.app.entity.cars.Status;
import com.app.app.repository.carRepo.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private StatusRepository statusRepository;
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public String addStatus(Status status) {
       statusRepository.save(status);

        return "done";
    }
}
