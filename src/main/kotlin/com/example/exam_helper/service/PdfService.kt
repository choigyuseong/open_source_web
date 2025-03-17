package com.example.exam_helper.service

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

// PDF 파일 처리와 관련된 핵심 비즈니스 로직을 구현합니다. PDFBox 같은 라이브러리를 이용해 PDF 내용을 추출하는 역할
@Service
class PdfService(private val summarizationService: SummarizationService) {

    fun processPdf(file: MultipartFile): String {
        // PDF 파일에서 텍스트 추출
        val text = file.inputStream.use { inputStream ->
            PDDocument.load(inputStream).use { document ->
                val stripper = PDFTextStripper()
                stripper.getText(document)
            }
        }
        // 추출한 텍스트를 요약
        return summarizationService.summarizeText(text)
    }
}