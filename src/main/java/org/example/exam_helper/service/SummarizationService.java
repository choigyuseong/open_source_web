package org.example.exam_helper.service;

import org.example.exam_helper.util.TextRankSummarizer;
import org.springframework.stereotype.Service;

@Service
public class SummarizationService {
    public String summarizeText(String text, int topN) {
        return TextRankSummarizer.summarize(text, topN);
    }
}
