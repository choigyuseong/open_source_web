package com.example.exam_helper.serivce

import com.example.exam_helper.service.SummarizationService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SummarizationServiceTest {

    private val summarizationService = SummarizationService()

    @Test
    fun testSummarizeText() {
        val inputText = """
            인공지능은 현대 사회에서 빠르게 발전하고 있는 기술입니다.
            많은 기업들이 인공지능 기술을 활용하여 다양한 문제를 해결하고 있습니다.
            이 기술은 의료, 금융, 교육 등 여러 분야에서 혁신적인 변화를 이끌고 있습니다.
            동시에 인공지능의 윤리적 문제와 사회적 영향에 대한 논의도 활발히 진행되고 있습니다.
            따라서 인공지능 기술에 대한 연구와 개발은 앞으로도 계속 중요해질 것입니다.
        """.trimIndent()

        val summary = summarizationService.summarizeText(inputText)
        println("요약 결과: $summary")
        // 간단한 검증: 요약 결과가 빈 문자열이 아니어야 함
        assertFalse(summary.isEmpty())
    }
}