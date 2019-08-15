package com.example.restclient.services;

import com.example.restclient.json.JokeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
@RestController
public class JokeService {

    private static final String BASE = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";

    private RestTemplate restTemplate;

    @Autowired

    public JokeService(RestTemplateBuilder builder){
        restTemplate = builder.build();
    }

    @GetMapping("/joke")
    public String getJoke(@RequestParam(required=false, defaultValue = "Chuck") String first,
                          @RequestParam(required=false, defaultValue = "Norris") String last){
        String url = String.format("%s&firstName=%s&lastName=%s", BASE, first, last);
        JokeResponse response = restTemplate.getForObject(url, JokeResponse.class);
        String output = "";
        if (response != null){
            output = response.getValue().getJoke();
        }
        return output;

    }
}
