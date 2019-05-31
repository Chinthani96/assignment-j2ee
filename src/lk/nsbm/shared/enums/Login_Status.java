package lk.nsbm.shared.enums;

public enum Login_Status {
    SUCCESS(1, "SUCCESS"),
    FAILED(2, "FAILED"),
    LOCKED(3, "LOCKED"),
    DEACTIVATED(4, "DEACTIVATED"),
    INVALID_PASSWORD(5,"INVALID_PASSWORD"),
    INVALID_USERNAME(6,"INVALID_USERNAME");

    private Integer value;
    private String label;

    private Login_Status(Integer value, String label) {
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
