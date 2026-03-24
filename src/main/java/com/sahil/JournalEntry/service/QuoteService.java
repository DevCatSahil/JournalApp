package com.sahil.JournalEntry.service;

import com.sahil.JournalEntry.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuoteService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api}")
    private String API;

    @Value("${api.key}")
    private String apiKey;

    public List<Quote> getQuotes(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key",apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Quote>> exchange = restTemplate.exchange(API, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Quote>>() {});
        List<Quote> quote= exchange.getBody();

        return quote;

    }
}
