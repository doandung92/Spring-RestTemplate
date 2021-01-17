package com.example.demo.service;

import com.example.demo.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.*;
import java.util.*;

@Service
public class RestService {
    private String url = "http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    public List<Card> findAll(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<Card[]> response = restTemplate.getForEntity(builder.build().encode().toUri()
                , Card[].class);

        return Arrays.asList(Optional.ofNullable(response.getBody()).orElse(new Card[0]));
    }

    public Card save(Card card){

        String url = "http://localhost:8080";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Card> request = new HttpEntity<>(card,headers);

        Map<String, Object> map = new HashMap<>();
        map.put("id",1);

        for (String key : map.keySet()) {
            builder.queryParam(key, map.get(key));
        }

        ResponseEntity<Card> response = restTemplate.postForEntity(builder.build().encode().toUri(),request,Card.class);
        return response.getBody();
    }

    public <T> ResponseEntity<T> restExchange(URI uri,
                                              HttpMethod method,
                                              HttpEntity<T> request,
                                              ParameterizedTypeReference<T> typeReference ){
        ResponseEntity<T> response = restTemplate.exchange(uri,method, request, typeReference);
        return response;
    }

}
