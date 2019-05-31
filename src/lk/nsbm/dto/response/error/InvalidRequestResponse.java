package lk.nsbm.dto.response.error;

public class InvalidRequestResponse {
    private String message;
    private String required;

    public InvalidRequestResponse() {
    }

    public InvalidRequestResponse(String message, String required) {
        this.message = message;
        this.required = required;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}
