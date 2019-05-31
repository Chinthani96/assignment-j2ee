package lk.nsbm.shared.enums;

public enum Status {
    ACTIVE(1, "ACTIVE"),
    INACTIVE(2, "INACTIVE"),
    DELETED(3, "DELETED");

    private Integer value;
    private String label;

    Status(Integer value, String label) {
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
