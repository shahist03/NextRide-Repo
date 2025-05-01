package com.app.app.service.evalutionService;

import com.app.app.entity.evalution.CustomerVisit;
import com.app.app.repository.evalutionRepo.CustomerVisitRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerVisitService {

    private final CustomerVisitRepository customerVisitRepository;

    public CustomerVisitService(CustomerVisitRepository customerVisitRepository) {
        this.customerVisitRepository = customerVisitRepository;
    }

    public CustomerVisit addCustomerVisit(CustomerVisit customerVisit) {
        return customerVisitRepository.save(customerVisit);
    }
}
