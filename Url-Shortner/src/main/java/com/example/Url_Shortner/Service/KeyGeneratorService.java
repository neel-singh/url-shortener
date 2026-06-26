package com.example.Url_Shortner.Service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KeyGeneratorService {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-";
    private final Random random = new Random();


    // Generate the short url key
    public String keyGenerator() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 7; i++){
            int idx = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(idx));
        }

        return sb.toString();
    }
}
