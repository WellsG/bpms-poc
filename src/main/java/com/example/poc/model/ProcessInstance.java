package com.example.poc.model;

public class ProcessInstance {

    private long processInstanceId;
    private String processName;
    private String start;
    private String end;

    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public long getProcessInstanceId() {
        return processInstanceId;
    }
    public void setProcessInstanceId(long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }

}
