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
@Entity
@Table(name = "customerdb")
public class CustomerDB implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "customerjson")
    private String customerJson;

    @Column(name = "modify_date")
    private String modifyDate;

    public CustomerDB(String customerJson,String modifyDate) {
        this.customerJson = customerJson;
        this.modifyDate=modifyDate;
    }

    public CustomerDB(int id,
                      String customerJson,String modifyDate) {
        this.id = id;
        this.customerJson = customerJson;
        this.modifyDate=modifyDate;
    }

    @Override
    public String toString() {
        return "CustomerDB{" +
               "id=" + id +
               ", customerJson='" + customerJson + '\'' +
               '}';
    }

    public CustomerDB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerJson() {
        return customerJson;
    }

    public void setCustomerJson(String customerJson) {
        this.customerJson = customerJson;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
