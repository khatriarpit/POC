package com.test.batch.writer;

import com.test.batch.model.CustomerCSV;
import com.test.batch.model.CustomerDB;
import com.test.batch.model.Person;
import com.test.batch.repository.CustomerDBRepository;
import com.test.batch.repository.PersonRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class PersonItemWriter implements ItemWriter<CustomerDB> {

    @Autowired
    CustomerDBRepository customerDBRepository;

    @Override
    public void write(List<? extends CustomerDB> items) throws Exception {
//        for(CustomerDB result : items) {
            customerDBRepository.save(items);
//        }
    }
}
