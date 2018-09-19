package com.test.batch.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerPhoneNumbers {

    String phoneType;
    String phoneNumber;
    boolean hasTcpaCnsnt;
    @JsonProperty("isPrimary")
    boolean isPrimary;
    @JsonIgnore
    @JsonProperty("smsStatus")
    SMSStatus smsStatus;
    @JsonIgnore
    @JsonProperty("voiceStatus")
    VoiceStatus voiceStatus;

    private String timeUpdated;

    public boolean getisPrimary() {
        return isPrimary;
    }

    public void setisPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
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

    public SMSStatus getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(SMSStatus smsStatus) {
        this.smsStatus = smsStatus;
    }

    public VoiceStatus getVoiceStatus() {
        return voiceStatus;
    }

    public void setVoiceStatus(VoiceStatus voiceStatus) {
        this.voiceStatus = voiceStatus;
    }


}
