package com.chem.backend.repository;

import com.chem.backend.model.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdfDocumentRepository extends JpaRepository<PdfDocument, Long> {

    List<PdfDocument> findByContentContainingIgnoreCase(String keyword);
}
