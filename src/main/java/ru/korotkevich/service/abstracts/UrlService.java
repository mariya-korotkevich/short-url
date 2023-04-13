package ru.korotkevich.service.abstracts;

import java.util.Optional;

public interface UrlService {
    String addShortUrl(String originalUrl);

    Optional<String> getOriginalUrl(String shortId);

    void delete(String shortId);
}
