package com.test.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class CustomerCSV implements Serializable {

    private int customerId;
    private String DncPhonList;
    private String InvalidPhonelist;
    private int cust_svcg_cntxt_id;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDncPhonList() {
        return DncPhonList;
    }

    public void setDncPhonList(String dncPhonList) {
        DncPhonList = dncPhonList;
    }

    public String getInvalidPhonelist() {
        return InvalidPhonelist;
    }

    public void setInvalidPhonelist(String invalidPhonelist) {
        InvalidPhonelist = invalidPhonelist;
    }

    public int getCust_svcg_cntxt_id() {
        return cust_svcg_cntxt_id;
    }

    public void setCust_svcg_cntxt_id(int cust_svcg_cntxt_id) {
        this.cust_svcg_cntxt_id = cust_svcg_cntxt_id;
    }

    public CustomerCSV() {
    }

    public CustomerCSV(int customerId,
                       String dncPhonList,
                       String invalidPhonelist,
                       int cust_svcg_cntxt_id) {
        this.customerId = customerId;
        DncPhonList = dncPhonList;
        InvalidPhonelist = invalidPhonelist;
        this.cust_svcg_cntxt_id = cust_svcg_cntxt_id;
    }
}
