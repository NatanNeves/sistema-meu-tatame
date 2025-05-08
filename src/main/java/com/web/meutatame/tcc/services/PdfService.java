package com.web.meutatame.tcc.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfService {

    public Resource getManualPdf() throws MalformedURLException {
        Path path = Paths.get("src/main/resources/static/Graduacao.pdf");
        return new UrlResource(path.toUri());
    }
}
