package lk.nsbm.service.custom;

import lk.nsbm.service.SuperService;

import javax.servlet.http.Part;

public interface FileUploadService extends SuperService {

    String uploadImage(Part filePart , int idNo);

    String uploadCV(Part filePart , int idNo);
}
