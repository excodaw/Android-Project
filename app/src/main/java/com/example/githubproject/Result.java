package com.example.githubproject;


public class Result {

    private int number;
    private String recordType;
    private String recordDate;

    public Result(int number, String recordType, String recordDate) {
        this.number = number;
        this.recordType = recordType;
        this.recordDate = recordDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        return "Result{" +
                "number=" + number +
                ", recordType='" + recordType + '\'' +
                ", recordDate='" + recordDate + '\'' +
                '}';
    }
}
