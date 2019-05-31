package lk.nsbm.shared.enums;

public enum User_Type {
    Company(1,"Company"), Student(2,"Student");

    private int id;
    private String label;

    User_Type(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}

