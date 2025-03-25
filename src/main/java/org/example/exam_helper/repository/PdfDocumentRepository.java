package org.example.exam_helper.repository;

import org.example.exam_helper.entity.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfDocumentRepository extends JpaRepository<PdfDocument, Long> {
    // 필요시 커스텀 메서드 추가 가능
}
