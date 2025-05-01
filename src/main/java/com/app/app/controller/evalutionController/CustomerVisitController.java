package com.app.app.controller.evalutionController;


import com.app.app.entity.evalution.CustomerVisit;
import com.app.app.service.evalutionService.CustomerVisitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers-visit")
public class CustomerVisitController {

    private final CustomerVisitService customerVisitService;

    public CustomerVisitController(CustomerVisitService customerVisitService) {
        this.customerVisitService = customerVisitService;
    }

    // Add a new customer visit
    @PostMapping("/add")
    public String addCustomerVisit(@RequestBody CustomerVisit customerVisit) {
       customerVisitService.addCustomerVisit(customerVisit);
       return "customerVisit added successfully";
    }
}
