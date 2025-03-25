package org.example.exam_helper.controller;

import org.example.exam_helper.entity.ImportantWord;
import org.example.exam_helper.repository.ImportantWordRepository;
import org.example.exam_helper.service.KeywordExtractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/important-keywords")
public class ImportantKeywordController {

    private final ImportantWordRepository importantWordRepository;
    private final KeywordExtractionService keywordExtractionService;

    public ImportantKeywordController(ImportantWordRepository importantWordRepository,
                                      KeywordExtractionService keywordExtractionService) {
        this.importantWordRepository = importantWordRepository;
        this.keywordExtractionService = keywordExtractionService;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getImportantKeywords() {
        Set<String> words = importantWordRepository.findAll().stream()
                .map(ImportantWord::getWord)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(words);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<Void> updateImportantKeywords(
            @PathVariable Long documentId,
            @RequestBody Set<String> newWords
    ) {
        keywordExtractionService.updateImportantWords(documentId, newWords);
        return ResponseEntity.noContent().build();
    }
}