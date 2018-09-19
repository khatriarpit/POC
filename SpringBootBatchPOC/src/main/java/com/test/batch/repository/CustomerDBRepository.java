package com.test.batch.repository;

import com.test.batch.model.CustomerDB;
import com.test.batch.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
@Transactional
@Repository
public interface CustomerDBRepository extends JpaRepository<CustomerDB, Integer> {
}
