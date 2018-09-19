package com.test.batch.model;

import java.util.List;


public class Customer {

    List<BusinessPhoneNumbers> businessPhoneNumbers;
    List<CustomerPhoneNumbers> customerPhoneNumbers;

    public List<BusinessPhoneNumbers> getBusinessPhoneNumbers() {
        return businessPhoneNumbers;
    }

    public void setBusinessPhoneNumbers(List<BusinessPhoneNumbers> businessPhoneNumbers) {
        this.businessPhoneNumbers = businessPhoneNumbers;
    }

    public List<CustomerPhoneNumbers> getCustomerPhoneNumbers() {
        return customerPhoneNumbers;
    }

    public void setCustomerPhoneNumbers(List<CustomerPhoneNumbers> customerPhoneNumbers) {
        this.customerPhoneNumbers = customerPhoneNumbers;
    }

    @Override
    public String toString() {
        return "Customer{" +
               "businessPhoneNumbers=" + businessPhoneNumbers +
               ", customerPhoneNumbers=" + customerPhoneNumbers +
               '}';
    }
}
