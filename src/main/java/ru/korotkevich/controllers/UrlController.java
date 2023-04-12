package ru.korotkevich.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.korotkevich.service.abstracts.UrlService;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody String originalUrl){
        return ResponseEntity.ok(urlService.getShortUrl(originalUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable String id){
        return ResponseEntity.ok(urlService.getOriginalUrl(id));
    }
}