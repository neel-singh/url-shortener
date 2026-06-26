package com.example.Url_Shortner.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "url")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String urlId;

    @Column(nullable = false)
    private String url;

}
