package ru.korotkevich.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.korotkevich.service.abstracts.UrlService;

@RestController
@RequestMapping("/api/shortUrl")
public class ShorterUrlController {

    private final UrlService urlService;

    public ShorterUrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @Operation(summary = "Создает новый короткий адрес. Если адрес уже есть, то возвращает существующий короткий адрес")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String originalUrl) {
        return ResponseEntity.ok(urlService.addShortUrl(originalUrl));
    }

    @Operation(summary = "Удаляет короткий адрес")
    @DeleteMapping("/{shortId}")
    public ResponseEntity<Void> delete(@PathVariable String shortId){
        urlService.delete(shortId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}