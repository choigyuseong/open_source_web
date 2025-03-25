package org.example.exam_helper.repository;

import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ImportantWordRepository {
    private final Set<String> importantWords = new HashSet<>();

    public void addWord(String word) {
        importantWords.add(word.toLowerCase());
    }

    public void addWords(Set<String> words) {
        importantWords.addAll(words.stream().map(String::toLowerCase).collect(Collectors.toSet()));
    }

    public Set<String> getAll() {
        return importantWords;
    }

    public void updateWords(Set<String> newWords) {
        importantWords.clear();
        importantWords.addAll(newWords.stream().map(String::toLowerCase).collect(Collectors.toSet()));
    }
}
