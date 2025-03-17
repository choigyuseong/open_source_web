package com.example.exam_helper.service

import org.springframework.stereotype.Service
import com.example.exam_helper.util.TextRankSummarizer

//텍스트 요약 및 단어 치환 같은 기능을 담당
//필요에 따라 앞서 구현한 TextRank 알고리즘을 호출하여 요약 기능을 수행
@Service
class SummarizationService {


    fun summarizeText(text: String): String {
        // 요약할 문장 수를 상황에 맞게 조절가능하다.
        return TextRankSummarizer.summarize(text, topN = 3)
    }

    // 특정 단어를 빈칸("_____")으로 대체하는 기능
    fun replaceWordsWithBlanks(text: String, wordsToReplace: List<String>): String {
        var modifiedText = text
        wordsToReplace.forEach { word ->
            modifiedText = modifiedText.replace(word, "_____")
        }
        return modifiedText
    }
}