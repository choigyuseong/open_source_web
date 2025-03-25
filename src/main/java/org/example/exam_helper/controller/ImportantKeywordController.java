package org.example.exam_helper.controller;

import org.example.exam_helper.repository.ImportantWordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/important-keywords")
public class ImportantKeywordController {

    private final ImportantWordRepository importantWordRepository;

    public ImportantKeywordController(ImportantWordRepository importantWordRepository) {
        this.importantWordRepository = importantWordRepository;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getImportantKeywords() {
        return ResponseEntity.ok(importantWordRepository.getAll());
    }

    @PutMapping
    public ResponseEntity<Void> updateImportantKeywords(@RequestBody Set<String> newWords) {
        importantWordRepository.updateWords(newWords);
        return ResponseEntity.noContent().build();
    }
}
