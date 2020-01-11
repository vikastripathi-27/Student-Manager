package com.nextlink.studentmanager;

public class timeline_model {

    private int id;
    private String sub_name;
    private String timestamp;
    private String action;

    public timeline_model() {

    }

    public timeline_model(int id, String sub_name, String timestamp, String action) {
        this.id = id;
        this.sub_name = sub_name;
        this.timestamp = timestamp;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
