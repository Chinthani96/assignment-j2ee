package lk.nsbm.dto.response.error;

public class ImageUploadErrorDTO {

    private String errormessage;
    private String status;

    public ImageUploadErrorDTO() {
    }

    public ImageUploadErrorDTO(String errormessage, String status) {
        this.errormessage = errormessage;
        this.status = status;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
