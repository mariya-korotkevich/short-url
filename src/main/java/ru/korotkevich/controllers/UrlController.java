package ru.korotkevich.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.korotkevich.service.abstracts.UrlService;

import java.util.function.Function;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody String originalUrl) {
        return ResponseEntity.ok(urlService.getShortUrl(originalUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return urlService.getOriginalUrl(id).map((Function<String, ResponseEntity<String>>) s -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Location", s);
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}