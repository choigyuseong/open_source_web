package org.example.exam_helper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Repository
public class PdfFileRepository {
    private final String storageDir = System.getProperty("user.dir") + File.separator + "pdf_storage";

    public PdfFileRepository() {
        File dir = new File(storageDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public String savePdf(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            fileName = "uploaded.pdf";
        }
        File destFile = new File(storageDir, fileName);
        file.transferTo(destFile);
        return destFile.getAbsolutePath();
    }
}
