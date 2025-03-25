package org.example.exam_helper.util;

import java.util.*;
import java.util.stream.Collectors;

public class TextRankKeywordExtractor {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            // 기본적인 불용어 목록
            "the", "is", "at", "of", "on", "and", "a", "an", "in", "to", "for", "with", "by", "that", "this", "it",
            "are", "was", "were", "be", "been", "has", "had", "do", "does", "did"
    ));

    private static List<String> tokenize(String text) {
        return Arrays.stream(text.toLowerCase().split("\\W+"))
                .filter(s -> !s.trim().isEmpty() && !STOP_WORDS.contains(s))
                .collect(Collectors.toList());
    }

    private static Map<String, Set<String>> buildGraph(List<String> words, int windowSize) {
        Map<String, Set<String>> graph = new HashMap<>();
        int size = words.size();
        for (int i = 0; i < size; i++) {
            String word = words.get(i);
            int start = Math.max(0, i - windowSize);
            int end = Math.min(size - 1, i + windowSize);
            for (int j = start; j <= end; j++) {
                if (i == j) continue;
                String neighbor = words.get(j);
                graph.computeIfAbsent(word, k -> new HashSet<>()).add(neighbor);
            }
        }
        return graph;
    }

    private static Map<String, Double> pageRank(Map<String, Set<String>> graph, double d, double minDiff, int maxIterations) {
        Map<String, Double> scores = new HashMap<>();
        for (String word : graph.keySet()) {
            scores.put(word, 1.0);
        }
        for (int iter = 0; iter < maxIterations; iter++) {
            Map<String, Double> newScores = new HashMap<>();
            double maxDiffScore = 0.0;
            for (String word : graph.keySet()) {
                double sum = 0.0;
                for (String neighbor : graph.get(word)) {
                    Set<String> neighborEdges = graph.get(neighbor);
                    if (neighborEdges != null && !neighborEdges.isEmpty()) {
                        sum += scores.get(neighbor) / neighborEdges.size();
                    }
                }
                double newScore = (1 - d) + d * sum;
                newScores.put(word, newScore);
                maxDiffScore = Math.max(maxDiffScore, Math.abs(newScore - scores.get(word)));
            }
            scores = newScores;
            if (maxDiffScore < minDiff) break;
        }
        return scores;
    }

    public static List<String> extractKeywords(String text, int topN) {
        List<String> words = tokenize(text);
        if (words.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, Set<String>> graph = buildGraph(words, 4);
        Map<String, Double> scores = pageRank(graph, 0.85, 0.0001, 100);
        return scores.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
