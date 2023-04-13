package ru.korotkevich.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.korotkevich.service.abstracts.UrlService;

import java.util.function.Function;

@RestController
public class RedirectController {

    private final UrlService service;

    public RedirectController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/{shortId}")
    public ResponseEntity<String> get(@PathVariable String shortId) {
        return service.getOriginalUrl(shortId).map((Function<String, ResponseEntity<String>>) s -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Location", s);
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}