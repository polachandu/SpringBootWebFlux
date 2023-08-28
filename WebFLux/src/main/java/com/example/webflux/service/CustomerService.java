package com.example.webflux.service;

import com.example.webflux.dao.CustomerDao;
import com.example.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> customerList() {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Processing time: "+(end-start));
        return customers;
    }

    public Flux<Customer> getAllCustomersFlux(){
        long start = System.currentTimeMillis();
        Flux<Customer> customersFlux = dao.getCustomersFlux();
        long end = System.currentTimeMillis();
        System.out.println("Processing time: "+(end-start));
        return customersFlux;
    }
}
