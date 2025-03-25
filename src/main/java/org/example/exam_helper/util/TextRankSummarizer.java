package org.example.exam_helper.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextRankSummarizer {

    public static double sentenceSimilarity(String s1, String s2) {
        List<String> words1 = Arrays.stream(s1.toLowerCase().split("\\W+"))
                .filter(w -> !w.isEmpty())
                .collect(Collectors.toList());
        List<String> words2 = Arrays.stream(s2.toLowerCase().split("\\W+"))
                .filter(w -> !w.isEmpty())
                .collect(Collectors.toList());
        if (words1.isEmpty() || words2.isEmpty()) {
            return 0.0;
        }
        long commonCount = words1.stream().distinct().filter(words2::contains).count();
        double denominator = Math.log(words1.size() + 1) + Math.log(words2.size() + 1);
        return commonCount / denominator;
    }

    public static String summarize(String text, int topN) {
        List<String> sentences = Arrays.stream(text.split("(?<=[.!?])\\s+"))
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.toList());
        int n = sentences.size();
        if (n == 0) {
            return "";
        }
        double[][] simMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                simMatrix[i][j] = (i == j) ? 0.0 : sentenceSimilarity(sentences.get(i), sentences.get(j));
            }
        }
        double d = 0.85;
        double minDiff = 0.001;
        double[] scores = new double[n];
        Arrays.fill(scores, 1.0);
        while (true) {
            double[] newScores = new double[n];
            Arrays.fill(newScores, 1 - d);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double sumSim = 0.0;
                    for (double sim : simMatrix[j]) {
                        sumSim += sim;
                    }
                    if (simMatrix[j][i] != 0.0 && sumSim != 0.0) {
                        newScores[i] += d * (scores[j] * (simMatrix[j][i] / sumSim));
                    }
                }
            }
            double maxDiffVal = 0.0;
            for (int i = 0; i < n; i++) {
                maxDiffVal = Math.max(maxDiffVal, Math.abs(newScores[i] - scores[i]));
                scores[i] = newScores[i];
            }
            if (maxDiffVal < minDiff) break;
        }
        List<SentenceScore> sentenceScores = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sentenceScores.add(new SentenceScore(sentences.get(i), scores[i]));
        }
        List<String> topSentences = sentenceScores.stream()
                .sorted((s1, s2) -> Double.compare(s2.score, s1.score))
                .limit(topN)
                .map(s -> s.sentence)
                .collect(Collectors.toList());
        List<String> summary = sentences.stream()
                .filter(topSentences::contains)
                .collect(Collectors.toList());
        return String.join(" ", summary);
    }

    private static class SentenceScore {
        String sentence;
        double score;
        SentenceScore(String sentence, double score) {
            this.sentence = sentence;
            this.score = score;
        }
    }
}
