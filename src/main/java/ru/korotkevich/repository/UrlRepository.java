package ru.korotkevich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korotkevich.models.Url;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findUrlByOriginalUrl(String originalUrl);
    Optional<Url> findUrlByShortId(String shortId);
}