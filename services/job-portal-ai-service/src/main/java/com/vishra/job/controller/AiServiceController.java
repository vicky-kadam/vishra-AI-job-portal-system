package com.vishra.job.controller;

import com.vishra.job.config.client.GeminiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiServiceController {
    private final GeminiClient geminiClient;

    @GetMapping("/{prompt}")
    public ResponseEntity<String> testController(@PathVariable String prompt) throws Exception {
        String systemPrompt = """
                
                """;
        return ResponseEntity.ok(geminiClient.generateText(systemPrompt,prompt));
    }

}
