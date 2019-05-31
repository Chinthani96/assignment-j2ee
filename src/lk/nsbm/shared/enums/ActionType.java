package lk.nsbm.shared.enums;

public enum ActionType {
    ALL(0,"ALL"),
    ONE(1,"ONE"),
    SUBSCRIBED(2,"SUBSCRIBED");
    private final int value;
    private String label;

    ActionType(int value, String label) {
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
