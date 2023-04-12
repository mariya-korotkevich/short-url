package ru.korotkevich.controllers;

import org.springframework.web.bind.annotation.*;
import ru.korotkevich.service.abstracts.UrlService;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/create")
    public String create(@RequestBody String originalUrl){
        return urlService.getShortUrl(originalUrl);
    }

    @GetMapping("/{id}")
    public String get(@PathVariable String id){
        return urlService.getOriginalUrl(id);
    }
}