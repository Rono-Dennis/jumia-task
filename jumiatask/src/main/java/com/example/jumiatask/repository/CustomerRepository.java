package com.example.jumiatask.repository;

import com.example.jumiatask.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    @Query("FROM Customer b WHERE b.id=:searchText OR b.name=:searchText OR b.phone=:searchText order by b.name ASC")
    Page<Customer> findAllCustomers(Pageable pageable, @Param("searchText") String searchText);

    List<Customer> findAllByPhoneIsStartingWith(String countryPrefix);
}

