package lk.nsbm.service.custom.impl;

import lk.nsbm.service.custom.FileUploadService;

import javax.servlet.http.Part;
import java.io.*;

public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String uploadImage(Part filePart, int idNo) {
        OutputStream out = null;
        InputStream fileContent = null;
        String fileName = getName(filePart, FileType.IMAGE, idNo);

        if (!filePart.getContentType().startsWith("image")) {
            return "Wrong File type";
        }

        try {
            out = new FileOutputStream(new File("/images" + File.separator + fileName));
            fileContent = filePart.getInputStream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public String uploadCV(Part filePart, int idNo) {
        OutputStream out = null;
        InputStream fileContent = null;
        String fileName = getName(filePart, FileType.CV, idNo);

        if (!filePart.getContentType().startsWith("doument")) {
            return "Wrong File type";
        }

        try {
            out = new FileOutputStream(new File("/documents" + File.separator + fileName));
            fileContent = filePart.getInputStream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    private String getName(Part part, FileType fileType, int idNo) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return idNo + content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private enum FileType {
        IMAGE, CV
    }
}
