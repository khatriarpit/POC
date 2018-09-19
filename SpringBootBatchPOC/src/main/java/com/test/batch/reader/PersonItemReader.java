package com.test.batch.reader;

import com.test.batch.model.Customer;
import com.test.batch.model.CustomerCSV;
import com.test.batch.model.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class PersonItemReader implements ItemReader<FlatFileItemReader<CustomerCSV>> {

    @Override
    public FlatFileItemReader<CustomerCSV> read() throws Exception, UnexpectedInputException, ParseException,
                                                    NonTransientResourceException {
        FlatFileItemReader<CustomerCSV> reader = new FlatFileItemReader<CustomerCSV>();
        reader.setResource(new ClassPathResource("test.csv"));
        reader.setLineMapper(new DefaultLineMapper<CustomerCSV>() {{
            setLineTokenizer(new DelimitedLineTokenizer(":") {{
                setNames(new String[] { "customerId", "DncPhonList","InvalidPhonelist","cust_svcg_cntxt_id" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(CustomerCSV.class);
            }});
        }});
        return reader;
    }
}
