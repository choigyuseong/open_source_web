package org.example.exam_helper.controller;

import org.example.exam_helper.repository.NonImportantWordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/non-important-keywords")
public class NonImportantKeywordController {

    private final NonImportantWordRepository nonImportantWordRepository;

    public NonImportantKeywordController(NonImportantWordRepository nonImportantWordRepository) {
        this.nonImportantWordRepository = nonImportantWordRepository;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getNonImportantKeywords() {
        return ResponseEntity.ok(nonImportantWordRepository.getAll());
    }

    @PutMapping
    public ResponseEntity<Void> updateNonImportantKeywords(@RequestBody Set<String> newWords) {
        nonImportantWordRepository.updateWords(newWords);
        return ResponseEntity.noContent().build();
    }
}
