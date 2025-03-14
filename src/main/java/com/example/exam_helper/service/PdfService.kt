package com.examhelper.service

import org.apache.pdfbox.pdmodel.PDDocument
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class PdfService {

    fun processPdf(file: MultipartFile) {
        file.inputStream.use { inputStream ->
            val document = PDDocument.load(inputStream)
            // 여기서 PDF 내용 추출, 요약, 단어 치환 등 필요한 작업 수행
            document.close()
        }
    }
}
