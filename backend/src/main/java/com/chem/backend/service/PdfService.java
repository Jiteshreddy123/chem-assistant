package com.chem.backend.service;

import com.chem.backend.model.PdfDocument;
import com.chem.backend.repository.PdfDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final PdfDocumentRepository repository;



    public PdfDocument uploadPdf(MultipartFile file) throws IOException {

        // Load PDF
        PDDocument document = PDDocument.load(file.getInputStream());

        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        document.close();

        // Save to DB
        PdfDocument pdf = PdfDocument.builder()
                .title(file.getOriginalFilename())
                .content(text)
                .build();

        return repository.save(pdf);
    }
    public List<PdfDocument> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<PdfDocument> getOne(Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    public List<PdfDocument> search(String query) {
        return repository.findByContentContainingIgnoreCase(query);
    }


}
