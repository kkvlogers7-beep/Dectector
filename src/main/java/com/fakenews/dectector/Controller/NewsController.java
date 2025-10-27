package com.fakenews.dectector.Controller;


import com.fakenews.dectector.Model.NewsRequest;
import com.fakenews.dectector.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*") // Allow frontend access
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * POST API to verify text or URL
     * Example: POST /api/news/verify
     * Request Body:
     * {
     *     "text": "Some news headline"
     * }
     * or
     * {
     *     "url": "https://somenewslink.com"
     * }
     */
    @PostMapping("/verify")
    public ResponseEntity<String> verifyNews(@RequestBody NewsRequest newsRequest) {
        try {
            String query;

            if (newsRequest.getText() != null && !newsRequest.getText().trim().isEmpty()) {
                query = newsRequest.getText().trim();
            } else if (newsRequest.getUrl() != null && !newsRequest.getUrl().trim().isEmpty()) {
                query = newsRequest.getUrl().trim();
            } else {
                return ResponseEntity.badRequest().body("❌ Please provide 'text' or 'url' to verify.");
            }

            String result = newsService.verifyNews(query);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("❌ Error verifying news: " + e.getMessage());
        }
    }

    /**
     * Simple test endpoint
     * GET /api/news/ping
     */
    @GetMapping("/ping")
    public String ping() {
        return "✅ Fake News Detector Backend is Running!";
    }
}
