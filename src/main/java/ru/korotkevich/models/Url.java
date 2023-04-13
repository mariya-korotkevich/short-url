package ru.korotkevich.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Url {

    public Url(String originalUrl, String shortId) {
        this.originalUrl = originalUrl;
        this.shortId = shortId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    @Column
    private String originalUrl;

    @Column
    private String shortId;

    @CreationTimestamp
    @Column
    private LocalDateTime persistDateTime;
}