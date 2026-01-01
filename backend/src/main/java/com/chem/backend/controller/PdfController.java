package com.chem.backend.controller;

import com.chem.backend.model.PdfDocument;
import com.chem.backend.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PdfController {

    private final PdfService pdfService;

    // ---------- UPLOAD ----------
    @PostMapping("/upload")
    public ResponseEntity<PdfDocument> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            PdfDocument saved = pdfService.uploadPdf(file);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ---------- GET ALL PDFs ----------
    @GetMapping
    public List<PdfDocument> getAll() {
        return pdfService.getAll();
    }

    // ---------- GET ONE PDF BY ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<PdfDocument> getOne(@PathVariable Long id) {
        return pdfService.getOne(id);
    }
    @GetMapping("/search")
    public List<PdfDocument> search(@RequestParam String q) {
        return pdfService.search(q);
    }

}
