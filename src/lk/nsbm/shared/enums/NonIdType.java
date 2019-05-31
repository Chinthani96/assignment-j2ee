package lk.nsbm.shared.enums;

public enum NonIdType {
    TYPE_ONE(1,"ONE"),
    TYPE_TWO(2,"TWO"),
    TYPE_THREE(3,"THREE");

    private int value;
    private String label;

    NonIdType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
