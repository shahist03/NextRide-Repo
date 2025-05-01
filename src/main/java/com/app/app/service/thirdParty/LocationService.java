package com.app.app.service.thirdParty;



import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class LocationService {

    public Map<String, Object> getLocationFromIp() {
        String url = "https://ipinfo.io/json";  // Fetches the IP-based location

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Map.class);
    }
}
