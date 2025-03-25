package org.example.exam_helper.service;

import org.example.exam_helper.repository.PdfFileRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PdfService {

    private final SummarizationService summarizationService;
    private final WordBlankService wordBlankService;
    private final PdfFileRepository pdfFileRepository;
    private final KeywordExtractionService keywordExtractionService;

    public PdfService(SummarizationService summarizationService, WordBlankService wordBlankService,
                      PdfFileRepository pdfFileRepository, KeywordExtractionService keywordExtractionService) {
        this.summarizationService = summarizationService;
        this.wordBlankService = wordBlankService;
        this.pdfFileRepository = pdfFileRepository;
        this.keywordExtractionService = keywordExtractionService;
    }

    public String processPdf(MultipartFile file) throws IOException {
        // 1. PDF 파일 저장
        String savedFilePath = pdfFileRepository.savePdf(file);
        System.out.println("PDF 파일 저장 경로: " + savedFilePath);

        // 2. 저장된 파일에서 PDF 텍스트 추출
        String extractedText;
        File savedFile = new File(savedFilePath);
        try (PDDocument document = PDDocument.load(savedFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            extractedText = stripper.getText(document);
        }

        // 3. 키워드 추출 및 Repository 저장
        keywordExtractionService.extractAndStoreKeywords(extractedText, 5);

        // 4. 요약 생성
        String summary = summarizationService.summarizeText(extractedText, 3);
        System.out.println("요약된 텍스트: " + summary);

        // 5. 요약 텍스트에서 중요 단어를 빈칸 처리
        String blankedSummary = wordBlankService.blankSummaryText(summary);
        System.out.println("빈칸 처리된 요약: " + blankedSummary);

        return blankedSummary;
    }
}
