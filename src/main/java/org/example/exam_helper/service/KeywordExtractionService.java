package org.example.exam_helper.service;

import org.example.exam_helper.entity.ImportantWord;
import org.example.exam_helper.entity.NonImportantWord;
import org.example.exam_helper.entity.PdfDocument;
import org.example.exam_helper.repository.ImportantWordRepository;
import org.example.exam_helper.repository.NonImportantWordRepository;
import org.example.exam_helper.repository.PdfDocumentRepository;
import org.example.exam_helper.util.TextRankKeywordExtractor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KeywordExtractionService {

    private final ImportantWordRepository importantWordRepository;
    private final NonImportantWordRepository nonImportantWordRepository;
    private final PdfDocumentRepository pdfDocumentRepository;

    public KeywordExtractionService(ImportantWordRepository importantWordRepository,
                                    NonImportantWordRepository nonImportantWordRepository,
                                    PdfDocumentRepository pdfDocumentRepository) {
        this.importantWordRepository = importantWordRepository;
        this.nonImportantWordRepository = nonImportantWordRepository;
        this.pdfDocumentRepository = pdfDocumentRepository;
    }

    public void extractAndStoreKeywords(String text, int topN) {
        List<String> keywords = TextRankKeywordExtractor.extractKeywords(text, topN + 10);
        Set<String> important = new HashSet<>(keywords.subList(0, Math.min(topN, keywords.size())));
        Set<String> nonImportant = new HashSet<>(keywords.subList(Math.min(topN, keywords.size()), keywords.size()));
        // (구버전 메모리 저장용일 경우 사용하던 코드)
    }

    public void updateImportantWords(Long documentId, Set<String> newWords) {
        PdfDocument document = pdfDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("문서가 존재하지 않습니다."));

        importantWordRepository.deleteAllByDocument(document);

        List<ImportantWord> words = newWords.stream()
                .map(w -> {
                    ImportantWord word = new ImportantWord();
                    word.setWord(w);
                    word.setDocument(document);
                    return word;
                })
                .collect(Collectors.toList());

        importantWordRepository.saveAll(words);
    }

    public void updateNonImportantWords(Long documentId, Set<String> newWords) {
        PdfDocument document = pdfDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("문서가 존재하지 않습니다."));

        nonImportantWordRepository.deleteAllByDocument(document);

        List<NonImportantWord> words = newWords.stream()
                .map(w -> {
                    NonImportantWord word = new NonImportantWord();
                    word.setWord(w);
                    word.setDocument(document);
                    return word;
                })
                .collect(Collectors.toList());

        nonImportantWordRepository.saveAll(words);
    }

}
