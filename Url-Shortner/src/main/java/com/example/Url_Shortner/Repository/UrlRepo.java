package com.example.Url_Shortner.Repository;

import com.example.Url_Shortner.Entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByUrlId(String newUrl);

    Optional<UrlEntity> findByUrl(String url);
}
