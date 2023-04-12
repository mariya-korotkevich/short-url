package ru.korotkevich.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korotkevich.models.Url;
import ru.korotkevich.repository.UrlRepository;
import ru.korotkevich.service.abstracts.UrlService;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;
    private final String host = "http://localhost:3031/";

    public UrlServiceImpl(UrlRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public String getShortUrl(String originalUrl) {
        Optional<Url> optionalUrl = repository.findUrlByOriginalUrl(originalUrl);
        if (optionalUrl.isPresent()){
            return optionalUrl.get().getShortUrl();
        } else {

            Url url = new Url();
            url.setOriginalUrl(originalUrl);
            url.setShortUrl(host + getNewId());
            url = repository.save(url);
            return url.getShortUrl();
        }
    }

    private String getNewId(){
        String result = randomString();
        if (repository.findUrlByShortUrl(result).isPresent()){
            result = getNewId();
        }
        return result;
    }

    private String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public Optional<String> getOriginalUrl(String id) {
        Optional<Url> optionalUrl = repository.findUrlByShortUrl(host + id);
        return optionalUrl.map(Url::getOriginalUrl);
    }

    @Override
    @Transactional
    public void delete(String shortId) {
        Optional<Url> optionalUrl = repository.findUrlByShortUrl(host + shortId);
        optionalUrl.ifPresent(repository::delete);
    }


}