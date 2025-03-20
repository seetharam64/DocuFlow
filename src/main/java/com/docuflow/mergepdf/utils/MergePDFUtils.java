package com.docuflow.mergepdf.utils;

import org.springframework.web.multipart.MultipartFile;

public class MergePDFUtils {

    public static boolean isValidPDF(MultipartFile file){
        return file.getContentType().equals("application/pdf");
    }
}
