package org.example.exam_helper.controller;

import org.example.exam_helper.entity.NonImportantWord;
import org.example.exam_helper.repository.NonImportantWordRepository;
import org.example.exam_helper.service.KeywordExtractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/non-important-keywords")
public class NonImportantKeywordController {

    private final NonImportantWordRepository nonImportantWordRepository;
    private final KeywordExtractionService keywordExtractionService;

    public NonImportantKeywordController(NonImportantWordRepository nonImportantWordRepository,
                                         KeywordExtractionService keywordExtractionService) {
        this.nonImportantWordRepository = nonImportantWordRepository;
        this.keywordExtractionService = keywordExtractionService;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getNonImportantKeywords() {
        Set<String> words = nonImportantWordRepository.findAll().stream()
                .map(NonImportantWord::getWord)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(words);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<Void> updateNonImportantKeywords(
            @PathVariable Long documentId,
            @RequestBody Set<String> newWords
    ) {
        keywordExtractionService.updateNonImportantWords(documentId, newWords);
        return ResponseEntity.noContent().build();
    }
}
