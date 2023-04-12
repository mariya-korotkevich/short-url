package ru.korotkevich.service.abstracts;

public interface UrlService {
    String getShortUrl(String originalUrl);

    String getOriginalUrl(String id);
}
