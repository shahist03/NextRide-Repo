package com.app.app.service.evalutionService;

import com.app.app.entity.evalution.Agents;
import com.app.app.repository.evalutionRepo.AgentsRepository;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private AgentsRepository agentsRepository;

    public AgentService(AgentsRepository agentsRepository) {
        this.agentsRepository = agentsRepository;
    }

    public Agents addAgent(Agents agent) {
       return agentsRepository.save(agent);
    }
}
