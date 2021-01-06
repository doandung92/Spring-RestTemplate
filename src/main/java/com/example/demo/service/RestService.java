package com.example.demo.service;

import com.example.demo.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    public Card getAPI(){
        String url = "http://localhost:8080";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        Map<String, String> map = new HashMap<>();
        map.put("name","ABCD");
        map.put("content","Grade 1");

        for (String key : map.keySet()) {
            builder.queryParam(key, map.get(key));
        }

        ResponseEntity<Card> response = restTemplate.getForEntity(builder.build().encode().toUri()
                , Card.class);

        return response.getBody();
    }

    public Card postAPI(Card card) {
        String url = "http://localhost:8080";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<Card> response = restTemplate.postForEntity(builder.build().encode().toUri()
               ,request , Card.class);

        return response.getBody();
    }
}
