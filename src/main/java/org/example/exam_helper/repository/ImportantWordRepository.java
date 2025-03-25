package org.example.exam_helper.repository;

import org.example.exam_helper.entity.ImportantWord;
import org.example.exam_helper.entity.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportantWordRepository extends JpaRepository<ImportantWord, Long> {
    List<ImportantWord> findAllByDocument(PdfDocument document);
    void deleteAllByDocument(PdfDocument document);
}
