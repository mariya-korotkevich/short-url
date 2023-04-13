package ru.korotkevich.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korotkevich.models.Url;
import ru.korotkevich.repository.UrlRepository;
import ru.korotkevich.service.abstracts.UrlService;
import ru.korotkevich.utils.Util;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;
    private final String host = "http://localhost:3031/";

    public UrlServiceImpl(UrlRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public String addShortUrl(String originalUrl) {
        return repository.findUrlByOriginalUrl(originalUrl)
                .map(url -> shortUrl(url.getShortId()))
                .orElseGet(() -> {
                            String shortId = getNewId();
                            repository.save(new Url(originalUrl, shortId));
                            return shortUrl(shortId);
                        });
    }

    private String getNewId() {
        String result = Util.randomString();
        if (repository.findUrlByShortId(result).isPresent()) {
            result = getNewId();
        }
        return result;
    }

    private String shortUrl(String shortId){
        return host + shortId;
    }

    @Override
    public Optional<String> getOriginalUrl(String shortId) {
        return repository.findUrlByShortId(shortId)
                .map(Url::getOriginalUrl);
    }

    @Override
    @Transactional
    public void delete(String shortId) {
        Optional<Url> optionalUrl = repository.findUrlByShortId(shortId);
        optionalUrl.ifPresent(repository::delete);
    }
}