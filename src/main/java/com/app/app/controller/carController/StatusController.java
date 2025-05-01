package com.app.app.controller.carController;


import com.app.app.entity.cars.Status;
import com.app.app.service.carService.StatusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    // http://localhost:8080/api/v1/status/add-status

    @PostMapping("/add-status")
    public String addStatus(
            @RequestBody Status status
            ) {
        return statusService.addStatus(status);
    }
}
