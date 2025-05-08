package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.services.PdfService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;

@Controller
public class GraduacaoController {

    private final PdfService pdfService;

    public GraduacaoController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    // Renderiza o template HTML
    @GetMapping("/graduacao")
    public String verGraduacao() {
        return "graduacao/index"; // templates/graduacao.html
    }

    // Retorna o PDF
    @GetMapping("/pdf/graduacao")
    public ResponseEntity<Resource> servirPdf() throws MalformedURLException {
        Resource pdf = pdfService.getManualPdf(); // pode renomear o método se quiser
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
