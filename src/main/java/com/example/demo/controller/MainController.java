package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    RestService restService;

    @GetMapping
    public ResponseEntity<Card> getCard(String name,String content){
        Card card = new Card(name,content);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping
    public Card save(@RequestBody Card card){
        return card;
    }

    @GetMapping("/get")
    public Card getAPI(){
        return restService.getAPI();
    }

    @PostMapping("/post")
    public Card postAPI(@RequestBody Card card){
        return restService.postAPI(card);
    }
}
