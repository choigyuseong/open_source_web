package org.example.exam_helper.repository;

import org.example.exam_helper.entity.NonImportantWord;
import org.example.exam_helper.entity.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NonImportantWordRepository extends JpaRepository<NonImportantWord, Long> {
    List<NonImportantWord> findAllByDocument(PdfDocument document);
    void deleteAllByDocument(PdfDocument document);
}
