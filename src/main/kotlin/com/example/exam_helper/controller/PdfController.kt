package com.example.exam_helper.controller

import com.example.exam_helper.service.PdfService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


// 클라이언트의 HTTP 요청(예: PDF 파일 업로드 요청)을 처리하는 REST API 엔드포인트를 구현
// 주로 서비스 계층과 연동되어 비즈니스 로직을 호출
@RestController
@RequestMapping("/api/pdf")
class PdfController(private val pdfService: PdfService) {

    @PostMapping("/upload")
    fun uploadPdf(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        pdfService.processPdf(file)
        return ResponseEntity.ok("PDF processed successfully")
    }
}