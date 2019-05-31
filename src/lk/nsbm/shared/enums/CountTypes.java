package lk.nsbm.shared.enums;

public enum CountTypes {
    POSTS("post"),
    FILE_UPLOAD("file_upload"),
    COMMENT("comment"),
    WORKSHOP("workshop");

    private String label;

    CountTypes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
