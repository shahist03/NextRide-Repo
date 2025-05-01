package com.app.app.controller.evalutionController;


import com.app.app.entity.evalution.Agents;
import com.app.app.service.evalutionService.AgentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    //http://localhost:8080/api/v1/agents/add-agent?name=John
    @PostMapping("/add-agent")
    public String addAgent(
            @RequestBody Agents agents
            ) {
        agentService.addAgent(agents);
        return "Agent added successfully";
    }
}
