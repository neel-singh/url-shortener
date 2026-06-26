package com.example.Url_Shortner.Service;

import com.example.Url_Shortner.Entity.UrlEntity;
import com.example.Url_Shortner.Repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlGenerationService {

    @Autowired
    private final KeyGeneratorService generator;
    private final UrlRepo repo;


    // Constructor
    public UrlGenerationService(KeyGeneratorService generator, UrlRepo repo){
        this.generator = generator;
        this.repo = repo;
    }


    // Generate the short url key and save it to db
    public String shortenUrl(String url){

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        Optional<UrlEntity> existing = repo.findByUrl(url);

        String newUrl = generator.keyGenerator();

        if (existing.isPresent()) {

            UrlEntity entity = existing.get();
            entity.setUrlId(newUrl);
            repo.save(entity);

            return newUrl;
        }

        UrlEntity entity = new UrlEntity();

        entity.setUrl(url);
        entity.setUrlId(newUrl);
        repo.save(entity);

        return newUrl;
    }


    // get the original url by short url key
    public String getOriginalUrl(String newUrl){

        Optional<UrlEntity> url = repo.findByUrlId(newUrl);

        return url
                .map(UrlEntity::getUrl)
                .orElseThrow(() -> new IllegalArgumentException("Short URL not found or has expired!"));
    }

}
