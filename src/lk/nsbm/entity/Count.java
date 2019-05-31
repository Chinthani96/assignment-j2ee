package lk.nsbm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.nsbm.shared.annotations.IdValue;

@JsonIgnoreProperties(value = {"_id"})
public class Count {

    @IdValue(name = "tableFieldName")
    private String tableFieldName;
    private int seq;

    public Count() {
    }

    public Count(String tableFieldName, int seq) {
        this.tableFieldName = tableFieldName;
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int increaseSeq() {
        return this.seq = this.getSeq() + 1;
    }

    public String getTableFieldName() {
        return tableFieldName;
    }

    public void setTableFieldName(String tableFieldName) {
        this.tableFieldName = tableFieldName;
    }
}
