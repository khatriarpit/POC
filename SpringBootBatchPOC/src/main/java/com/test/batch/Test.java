package com.test.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.batch.model.Customer;
import com.test.batch.model.CustomerPhoneNumbers;
import com.test.batch.model.Reason;
import com.test.batch.model.SMSStatus;
import com.test.batch.model.VoiceStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

    public static void main(String a[]) throws IOException {
        String json = "{\n" +
                      "\t\"businessPhoneNumbers\": [\n" +
                      "\t],\n" +
                      "\t\"customerPhoneNumbers\": [{\n" +
                      "\t\t\"isPrimary\": true,\n" +
                      "\t\t\"phoneType\": \"mobile\",\n" +
                      "\t\t\"phoneNumber\": \"1234567890\",\n" +
                      "\t\t\"timeUpdated\": \"2012-01-21T12:11:11.158Z\",\n" +
                      "\t\t\"hasTcpaCnsnt\": true\n" +
                      "\t},\n" +
                      "\t{\n" +
                      "\t\"isPrimary\": true,\n" +
                      "    \t\t\"phoneType\": \"mobile\",\n" +
                      "    \t\t\"phoneNumber\": \"9974646245\",\n" +
                      "    \t\t\"timeUpdated\": \"2012-01-21T12:11:11.158Z\",\n" +
                      "    \t\t\"hasTcpaCnsnt\": true\n" +
                      "\n" +
                      "\t}]\n" +
                      "}";
        ObjectMapper objectMapper = new ObjectMapper();
        Customer     customer     = objectMapper.readValue(json, Customer.class);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        String str=objectMapper.writeValueAsString(customerList);
        System.out.println(str);
//        List<String> listofString = new ArrayList<>();
//        listofString.add("9974646245,78524544");
//
//        List<Customer> dncCustomerList = getDNCObject(customerList, listofString);
//        System.out.println(dncCustomerList.toString());
//
//        List<Customer> invalidCustomerList = getInvalidCustomerObject(customerList, listofString);
//        System.out.println(invalidCustomerList.toString());
    }

    private static List<Customer> getInvalidCustomerObject(List<Customer> customerList,
                                                           List<String> listofString) {
        for (String str : listofString) {
            String[] tokens = str.split(",");
            for (String mobileNo : tokens) {
                try {
                    for (Customer customer1 : customerList) {
                        for (int i = 0; i < customer1.getCustomerPhoneNumbers().size(); i++) {
                            if (customer1.getCustomerPhoneNumbers().get(i).getPhoneNumber().equals(mobileNo)) {
                                customer1.getCustomerPhoneNumbers().get(i).setSmsStatus(setStatusObject());
                                customer1.getCustomerPhoneNumbers().get(i).setVoiceStatus(setVoiceObject());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return customerList;
    }

    private static List<Customer> getDNCObject(List<Customer> customerList,
                                               List<String> listofString) {
        for (String str : listofString) {
            String[] tokens = str.split(",");
            for (String mobileNo : tokens) {
                try {
                    for (Customer customer1 : customerList) {
                        Iterator<CustomerPhoneNumbers> it = customer1.getCustomerPhoneNumbers().iterator();
                        while (it.hasNext()) {
                            if (it.next().getPhoneNumber().equals(mobileNo)) {
                                it.remove();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return customerList;
    }

    public static SMSStatus setStatusObject() {
        SMSStatus smsStatus = new SMSStatus();
        smsStatus.setReasons(getListOfReason());
        smsStatus.setStatus("INVALID");
        return smsStatus;
    }

    public static VoiceStatus setVoiceObject() {
        VoiceStatus voiceStatus = new VoiceStatus();
        voiceStatus.setReasons(getListOfReason());
        voiceStatus.setStatus("INVALID");
        return voiceStatus;
    }

    private static List<Reason> getListOfReason() {
        Reason reason = new Reason();
        reason.setReasonCode("INVALID");
        reason.setReasonDesc("Phone number is marked as invalid by agent");
        List<Reason> list = new ArrayList<>();
        list.add(reason);
        return list;
    }
}