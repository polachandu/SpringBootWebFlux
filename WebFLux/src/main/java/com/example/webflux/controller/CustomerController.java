package com.example.webflux.controller;

import com.example.webflux.dto.Customer;
import com.example.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers()  {
        return customerService.customerList();
    }

    @GetMapping("/allCustomersFlux")
    public Flux<Customer> getAllCustomersStream()  {
        return customerService.getAllCustomersFlux();
    }
}
