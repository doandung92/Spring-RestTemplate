package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.util.*;

import java.net.*;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    private RestService restService;

    @GetMapping
    public ResponseEntity<List<Card>> getAll(){
        List<Card> list = restService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card){
        Card card1 = restService.save(card);
        return new ResponseEntity<>(card1, HttpStatus.CREATED);
    }

    @GetMapping("/ex")
    public ResponseEntity<List<Card>> getAllByExchange(){
        String url = "http://localhost:8080";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().encode().toUri();
        ResponseEntity<List<Card>> response = restService.restExchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Card>>() {});
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping("/ex")
    public ResponseEntity<Card> saveByExchange(@RequestBody Card card){
        String url = "http://localhost:8080";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().encode().toUri();
        HttpEntity<Card> request = new HttpEntity<>(card);
        ResponseEntity<Card> response = restService.restExchange(uri,
                HttpMethod.POST, request, new ParameterizedTypeReference<Card>() {});
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}
