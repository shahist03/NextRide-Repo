package com.app.app.controller.evalutionController;

import com.app.app.entity.evalution.Agents;
import com.app.app.entity.evalution.Area;
import com.app.app.entity.evalution.CustomerVisit;
import com.app.app.repository.evalutionRepo.AreaRepository;
import com.app.app.repository.evalutionRepo.AgentsRepository;
import com.app.app.repository.evalutionRepo.CustomerVisitRepository;
import com.app.app.service.thirdParty.SmsService;
import com.app.app.service.thirdParty.WhatsAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {

    private AreaRepository areaRepository;
    private AgentsRepository agentsRepository;
    private CustomerVisitRepository customerVisitRepository;
    private SmsService smsService;
    private WhatsAppService whatsAppService;

    public CRMController(AreaRepository areaRepository, AgentsRepository agentsRepository, CustomerVisitRepository customerVisitRepository, SmsService smsService, WhatsAppService whatsAppService) {
        this.areaRepository = areaRepository;
        this.agentsRepository = agentsRepository;
        this.customerVisitRepository = customerVisitRepository;
        this.smsService = smsService;

        this.whatsAppService = whatsAppService;
    }

    @GetMapping("/areas")
    public ResponseEntity<List<Area>> searchAgents(
            @RequestParam int pinCode
    ){
        List<Area> areas = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PutMapping("/add-agent")
    public String allocateAgent(
            @RequestParam long customerId,
            @RequestParam long agentId
    ){
        Agents agent=null;
        Optional<Agents> opAgent = agentsRepository.findById(agentId);
        if(opAgent.isPresent()){
            agent=opAgent.get();
        }
        Optional<CustomerVisit> customerVisit= customerVisitRepository.findById(customerId);
        if(customerVisit.isPresent()){
            customerVisit.get().setAgents(agent);
            customerVisitRepository.save(customerVisit.get());
            smsService.sendSms("+919035192900","Agent is allocated");
            whatsAppService.sendWhatsAppMessage("+917411482176","Agent is allocated");
        }
        return "Agent allocated successfully";
    }
}
