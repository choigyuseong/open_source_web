package org.example.exam_helper.service;

import org.example.exam_helper.repository.ImportantWordRepository;
import org.example.exam_helper.repository.NonImportantWordRepository;
import org.example.exam_helper.util.TextRankKeywordExtractor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class KeywordExtractionService {

    private final ImportantWordRepository importantWordRepository;
    private final NonImportantWordRepository nonImportantWordRepository;

    public KeywordExtractionService(ImportantWordRepository importantWordRepository,
                                    NonImportantWordRepository nonImportantWordRepository) {
        this.importantWordRepository = importantWordRepository;
        this.nonImportantWordRepository = nonImportantWordRepository;
    }

    public void extractAndStoreKeywords(String text, int topN) {
        List<String> keywords = TextRankKeywordExtractor.extractKeywords(text, topN + 10);
        Set<String> important = new HashSet<>(keywords.subList(0, Math.min(topN, keywords.size())));
        Set<String> nonImportant = new HashSet<>(keywords.subList(Math.min(topN, keywords.size()), keywords.size()));
        importantWordRepository.addWords(important);
        nonImportantWordRepository.addWords(nonImportant);
    }
}
