package org.example.exam_helper.service;

import org.example.exam_helper.repository.ImportantWordRepository;
import org.springframework.stereotype.Service;

@Service
public class WordBlankService {

    private final ImportantWordRepository importantWordRepository;

    public WordBlankService(ImportantWordRepository importantWordRepository) {
        this.importantWordRepository = importantWordRepository;
    }

    private String blankWords(String text, Iterable<String> wordsToBlank) {
        String result = text;
        for (String word : wordsToBlank) {
            String regex = "\\b" + java.util.regex.Pattern.quote(word) + "\\b";
            result = result.replaceAll("(?i)" + regex, "_____");
        }
        return result;
    }

    public String blankPdfText(String text) {
        return blankWords(text, importantWordRepository.getAll());
    }

    public String blankSummaryText(String text) {
        return blankWords(text, importantWordRepository.getAll());
    }
}
