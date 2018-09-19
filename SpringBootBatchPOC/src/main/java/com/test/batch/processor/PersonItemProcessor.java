package com.test.batch.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.batch.model.Customer;
import com.test.batch.model.CustomerCSV;
import com.test.batch.model.CustomerDB;
import com.test.batch.model.CustomerPhoneNumbers;
import com.test.batch.model.Person;
import com.test.batch.model.Reason;
import com.test.batch.model.SMSStatus;
import com.test.batch.model.VoiceStatus;
import com.test.batch.repository.CustomerDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class PersonItemProcessor implements ItemProcessor<CustomerCSV, CustomerDB> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
    private List<Person> previousItems = new ArrayList<>();
    @Autowired
    CustomerDBRepository customerDBRepository;

    @Override
    public CustomerDB process(final CustomerCSV customerCSV) throws Exception {
        List<String> dncPhoneList = Arrays.asList(customerCSV.getDncPhonList().split("\\s*,\\s*"));
        List<String> invalidPhoneList = Arrays.asList(customerCSV.getInvalidPhonelist().split("\\s*,\\s*"));
        System.out.println("--------------------------------------?????????????????? >>>>>>>>>>>>>> ");
        List<CustomerDB> customerDbList = customerDBRepository.findAll();
        List<CustomerDB> customerdb = getDNCObject(customerDbList, dncPhoneList);
        List<CustomerDB> customerInvaliddb = getInvalidObject(customerDbList, invalidPhoneList);
        for (CustomerDB customerDBs : customerdb) {
            return new CustomerDB(customerDBs.getId(), customerDBs.getCustomerJson(), customerDBs.getModifyDate());
        }
//        return customerdb;
        return null;
    }

    private List<CustomerDB> getDNCObject(List<CustomerDB> customerDbList,
                                          List<String> dncPhoneList) {

        List<CustomerDB> listss = new ArrayList<>();
        for (String mobileNo : dncPhoneList) {
            try {
                for (CustomerDB customerDB : customerDbList) {
                    boolean flag = false;
                    ObjectMapper objectMapper = new ObjectMapper();
                    Customer customer = objectMapper.readValue(customerDB.getCustomerJson(), Customer.class);
                    List<Customer> customerList = new ArrayList<>();
                    customerList.add(customer);
                    for (Customer customer1 : customerList) {
                        Iterator<CustomerPhoneNumbers> it = customer1.getCustomerPhoneNumbers().iterator();
                        while (it.hasNext()) {
                            if (it.next().getPhoneNumber().equals(mobileNo)) {
                                flag = true;
                                it.remove();
                            }
                        }
                    }
                    if (flag) {
                        CustomerDB customerDBObj = new CustomerDB();
                        customerDBObj.setCustomerJson(objectMapper.writeValueAsString(customerList));
                        customerDBObj.setId(customerDB.getId());
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                        customerDBObj.setModifyDate(timeStamp);
                        listss.add(customerDBObj);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listss;
    }

    private static List<CustomerDB> getInvalidObject(List<CustomerDB> customerDbList,
                                                     List<String> invalidList) {
        List<CustomerDB> listss = new ArrayList<>();
        for (String mobileNo : invalidList) {
            try {
                for (CustomerDB customerDB : customerDbList) {
                    boolean flag = false;
                    ObjectMapper objectMapper = new ObjectMapper();
                    Customer customer = null;
                    try {
                        customer = objectMapper.readValue(customerDB.getCustomerJson(), Customer.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<Customer> customerList = new ArrayList<>();
                    customerList.add(customer);
                    for (Customer customer1 : customerList) {
                        for (int i = 0; i < customer1.getCustomerPhoneNumbers().size(); i++) {
                            if (customer1.getCustomerPhoneNumbers().get(i).getPhoneNumber().equals(mobileNo)) {
                                customer1.getCustomerPhoneNumbers().get(i).setSmsStatus(setStatusObject());
                                customer1.getCustomerPhoneNumbers().get(i).setVoiceStatus(setVoiceObject());
                                flag = true;
                            }
                        }
                    }
                    if (flag) {
                        listss.add(customerDB);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listss;
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