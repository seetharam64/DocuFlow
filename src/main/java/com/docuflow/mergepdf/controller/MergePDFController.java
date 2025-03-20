package com.docuflow.mergepdf.controller;

import com.docuflow.mergepdf.service.MergePDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class MergePDFController {

    @Autowired
    MergePDFService serivce;

    @PostMapping("/merge")
    public ResponseEntity<?> mergePdfs(@RequestParam("File") MultipartFile[] files) {
        try {
            
            byte[] mergePdf = serivce.mergePdfs(files);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=merged.pdf")
                    .body(mergePdf);
        } catch (Exception e) {
            throw new RuntimeException("Error merging PDFs");
        }
    }
}
