package com.example.restclient.services;

import com.example.restclient.entities.Site;
import com.example.restclient.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RestController
public class GeocderService {
    private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    private RestTemplate restTemplate;

    @Autowired
    public GeocderService(RestTemplateBuilder builder){
        restTemplate = builder.build();
    }

    @GetMapping("/geo")
    public Site getLatLng(@RequestParam(required = false,
            defaultValue="1600+Amphitheatre+Parkway,+Mountain+View,+CA") String... address) {
        String joinedAddress = String.join(".", address);
        String encodedAddress = "";
        encodedAddress = URLEncoder.encode(joinedAddress, StandardCharsets.UTF_8);

        Response response = restTemplate.getForObject(String.format("%s?address=%s&key=%s", BASE, encodedAddress, KEY), Response.class);
        return new Site(response.getFormattedAddress(),
                        response.getLocation().getLat(),
                        response.getLocation().getLng());
    }
}
