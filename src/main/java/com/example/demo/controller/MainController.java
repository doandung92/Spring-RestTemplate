package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    private List<Card> list = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Card>> getAll(){
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card){
        card.setId(list.size());
        list.add(card);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getAPI(@PathVariable Integer id){
        Card card = list.stream().filter(m->m.getId() == id).findFirst().orElseThrow(IllegalArgumentException::new);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> postAPI(@RequestBody Card card, @PathVariable Integer id){
        Card card1 = list.stream().filter(m->m.getId() == id).findFirst().orElseThrow(IllegalArgumentException::new);
        if (!card.getId().equals(card1.getId())) throw new IllegalArgumentException();
        list.remove(card1);
        return new ResponseEntity<>(card1, HttpStatus.ACCEPTED);
    }
}
