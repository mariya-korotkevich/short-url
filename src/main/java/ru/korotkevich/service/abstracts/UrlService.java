package ru.korotkevich.service.abstracts;

import java.util.Optional;

public interface UrlService {
    String getShortUrl(String originalUrl);

    Optional<String> getOriginalUrl(String id);
}
