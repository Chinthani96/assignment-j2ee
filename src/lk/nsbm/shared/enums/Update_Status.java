package lk.nsbm.shared.enums;

public enum Update_Status {

    SUCCESS(1, "SUCCESS"),
    FAILED(2, "FAILED"),
    INVALID_CREDENTIALS(3, "INVALID_CREDENTIALS"),
    USER_NOT_FOUND(4, "USER_NOT_FOUND"),
    PENDING(5, "PENDING"),
    NEW(6, "NEW"),
    NOT_FOUND(7,"NOT_FOUND");

    private Integer value;
    private String label;

    private Update_Status(Integer value, String label) {
        this.value = value;
        this.label = label;
    }


    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
