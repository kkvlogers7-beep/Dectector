package com.example.fakenews.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    public String verifyNews(String query) {
        try {
            // Replace with your actual Google Search API key + CX
            String apiKey = "YOUR_GOOGLE_API_KEY";
            String cx = "YOUR_SEARCH_ENGINE_ID";
            String url = "https://www.googleapis.com/customsearch/v1?q=" + query +
                    "&key=" + apiKey + "&cx=" + cx;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            if (response != null && response.contains("fact-check")) {
                return "🟢 This news seems REAL (verified sources found).";
            } else {
                return "🔴 This news might be FAKE (no reliable sources found).";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error verifying news: " + e.getMessage();
        }
    }
}
