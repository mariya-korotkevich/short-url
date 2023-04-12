package ru.korotkevich.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korotkevich.models.Url;
import ru.korotkevich.repository.UrlRepository;
import ru.korotkevich.service.abstracts.UrlService;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    @Transactional
    public String getShortUrl(String originalUrl) {
        Optional<Url> optionalUrl = urlRepository.findUrlByOriginalUrl(originalUrl);
        if (optionalUrl.isPresent()){
            return optionalUrl.get().getShortUrl();
        } else {
            return create(originalUrl);
        }
    }

    @Override
    public String getOriginalUrl(String id) {
        Optional<Url> optionalUrl = urlRepository.findUrlByShortUrl("http://localhost:3031/" + id);
        return optionalUrl.map(Url::getOriginalUrl).orElse(null);
    }

    private String create(String originalUrl) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);

        url = urlRepository.save(url);

        url.setShortUrl("http://localhost:3031/" + url.getId());

        return url.getShortUrl();
    }
}