package com.docuflow.mergepdf.service;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class MergePDFService {

    public byte[] mergePdfs(MultipartFile[] files) {
        try {
            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            for (MultipartFile file : files) {
                pdfMerger.addSource(file.getInputStream());
            }
            pdfMerger.setDestinationStream(outputStream);
            pdfMerger.mergeDocuments(null);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
