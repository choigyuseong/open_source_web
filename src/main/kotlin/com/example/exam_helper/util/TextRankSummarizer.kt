package com.example.exam_helper.util


import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.max

// TextRank 알고리즘 구현
object TextRankSummarizer {

    // 간단한 문장 유사도 계산 함수
    // 두 문장을 단어 단위로 분리한 후, 공통 단어의 수를 양 문장의 길이의 자연로그 값으로 정규화합니다.
    fun sentenceSimilarity(s1: String, s2: String): Double {
        val words1 = s1.lowercase().split("\\W+".toRegex()).filter { it.isNotBlank() }
        val words2 = s2.lowercase().split("\\W+".toRegex()).filter { it.isNotBlank() }
        if (words1.isEmpty() || words2.isEmpty()) return 0.0

        val commonWordsCount = words1.intersect(words2).size.toDouble()
        return commonWordsCount / (ln(words1.size.toDouble() + 1) + ln(words2.size.toDouble() + 1))
    }

    // TextRank 알고리즘을 이용한 요약 함수
    // text: 요약할 원문, topN: 선택할 문장의 수
    fun summarize(text: String, topN: Int = 3): String {
        // 1. 텍스트를 문장 단위로 분리 (간단한 정규식 활용)
        val sentences = text.split(Regex("(?<=[.!?])\\s+")).filter { it.isNotBlank() }
        val n = sentences.size
        if (n == 0) return ""

        // 2. 문장 간 유사도 행렬 생성
        val similarityMatrix = Array(n) { DoubleArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                similarityMatrix[i][j] = if (i == j) 0.0 else sentenceSimilarity(sentences[i], sentences[j])
            }
        }

        // 3. PageRank 알고리즘 적용
        val d = 0.85 // damping factor
        val minDiff = 0.001
        val scores = DoubleArray(n) { 1.0 } // 초기 점수 1.0으로 설정

        while (true) {
            val newScores = DoubleArray(n) { (1 - d) }
            for (i in 0 until n) {
                for (j in 0 until n) {
                    val sumSim = similarityMatrix[j].sum()
                    if (similarityMatrix[j][i] != 0.0 && sumSim != 0.0) {
                        newScores[i] += d * (scores[j] * (similarityMatrix[j][i] / sumSim))
                    }
                }
            }
            // 수렴 여부 확인
            var maxDiff = 0.0
            for (i in 0 until n) {
                maxDiff = max(maxDiff, abs(newScores[i] - scores[i]))
                scores[i] = newScores[i]
            }
            if (maxDiff < minDiff) break
        }

        // 4. 문장과 점수를 매핑하여 상위 문장 선택
        val sentenceScores = sentences.mapIndexed { index, sentence -> Pair(sentence, scores[index]) }
        val topSentences = sentenceScores.sortedByDescending { it.second }
            .take(topN)
            .map { it.first }

        // 원본 문장의 순서를 유지하도록 정렬 후 결합하여 요약문 생성
        val summary = sentences.filter { it in topSentences }
        return summary.joinToString(" ")
    }
}