package com.test.batch.model;

public class BusinessPhoneNumbers {
    private boolean isPrimary;
    private String phoneType;
    private String phoneNumber;
    private boolean hasTcpaCnsnt;

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isHasTcpaCnsnt() {
        return hasTcpaCnsnt;
    }

    public void setHasTcpaCnsnt(boolean hasTcpaCnsnt) {
        this.hasTcpaCnsnt = hasTcpaCnsnt;
    }

    @Override
    public String toString() {
        return "BusinessPhoneNumbers{" +
               "isPrimary=" + isPrimary +
               ", phoneType='" + phoneType + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", hasTcpaCnsnt=" + hasTcpaCnsnt +
               '}';
    }
}
