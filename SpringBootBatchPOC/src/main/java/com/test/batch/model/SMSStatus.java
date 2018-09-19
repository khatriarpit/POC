package com.test.batch.model;

import java.util.List;

public class SMSStatus {
    private String status;
    private List<Reason> reasons;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reason> getReasons() {
        return reasons;
    }

    public void setReasons(List<Reason> reasons) {
        this.reasons = reasons;
    }
}
