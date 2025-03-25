package org.example.exam_helper.controller;

import org.example.exam_helper.service.WordBlankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/text-blank")
public class TextBlankController {

    private final WordBlankService wordBlankService;

    public TextBlankController(WordBlankService wordBlankService) {
        this.wordBlankService = wordBlankService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<String> getBlankedPdfText(@RequestParam String text) {
        String result = wordBlankService.blankPdfText(text);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/summary")
    public ResponseEntity<String> getBlankedSummaryText(@RequestParam String text) {
        String result = wordBlankService.blankSummaryText(text);
        return ResponseEntity.ok(result);
    }
}
