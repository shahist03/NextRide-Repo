package com.app.app.controller;

import com.app.app.service.thirdParty.LocationService;
import com.app.app.service.thirdParty.ReverseGeocodingService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService ipLocationService;
    private final ReverseGeocodingService reverseGeocodingService;

    public LocationController(LocationService ipLocationService, ReverseGeocodingService reverseGeocodingService) {
        this.ipLocationService = ipLocationService;
        this.reverseGeocodingService = reverseGeocodingService;
    }

    // 📌 1️⃣ Get Location from IP
    @GetMapping("/ip-location")
    public Map<String, Object> getIpLocation() {
        return ipLocationService.getLocationFromIp();
    }

    // 📌 2️⃣ Convert Latitude & Longitude to an Address
    @PostMapping("/reverse-geocode")
    public Map<String, Object> getAddress(@RequestBody Map<String, String> coordinates) {
        String[] latLng = coordinates.get("loc").split(",");  // Extract latitude & longitude
        double latitude = Double.parseDouble(latLng[0]);
        double longitude = Double.parseDouble(latLng[1]);

        return reverseGeocodingService.getAddressFromCoordinates(latitude, longitude);
    }
}
