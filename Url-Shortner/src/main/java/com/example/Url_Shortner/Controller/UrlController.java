package com.example.Url_Shortner.Controller;

import com.example.Url_Shortner.Service.UrlGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private final UrlGenerationService urlService;

    // Constructor
    public UrlController(UrlGenerationService urlService){
        this.urlService = urlService;
    }


    //
    @PostMapping("api/v1")
    public ResponseEntity<String> shortenUrl(@RequestBody String url){

        String newUrl = urlService.shortenUrl(url);

        return ResponseEntity.ok(newUrl);
    }


    //
    @GetMapping("neels/{urlId}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String urlId){
        String originalUrl = urlService.getOriginalUrl(urlId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
