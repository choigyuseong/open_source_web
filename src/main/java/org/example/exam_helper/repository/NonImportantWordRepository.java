package org.example.exam_helper.repository;

import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class NonImportantWordRepository {
    private final Set<String> nonImportantWords = new HashSet<>();

    public void addWord(String word) {
        nonImportantWords.add(word.toLowerCase());
    }

    public void addWords(Set<String> words) {
        nonImportantWords.addAll(words.stream().map(String::toLowerCase).collect(Collectors.toSet()));
    }

    public Set<String> getAll() {
        return nonImportantWords;
    }

    public void updateWords(Set<String> newWords) {
        nonImportantWords.clear();
        nonImportantWords.addAll(newWords.stream().map(String::toLowerCase).collect(Collectors.toSet()));
    }
}
