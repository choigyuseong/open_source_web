package com.examhelper.service

import org.springframework.stereotype.Service

@Service
class SummarizationService {

    // 간단한 요약 예제 (실제 요약 알고리즘은 필요에 따라 구현)
    fun summarize(text: String): String {
        return text.substring(0, kotlin.math.min(100, text.length))
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