package com.example.webflux.handler;

import com.example.webflux.dao.CustomerDao;
import com.example.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao dao;

    public Mono<ServerResponse> getCustomers(ServerRequest request){
        Flux<Customer> customersFluxWithDelay = dao.getCustomersFluxWithDelay();

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersFluxWithDelay,Customer.class);
    }

    public Mono<ServerResponse> findCustomers(ServerRequest request){
        int customerId = Integer.valueOf(request.pathVariable("input"));
//        dao.getCustomersFlux().filter(i -> i.getId() == customerId).take(1).single();
        Mono<Customer> customerMono = dao.getCustomersFlux().filter(i->i.getId() == customerId).next();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ": "+dto.getName());
        return  ServerResponse.ok()
                .body(saveResponse, String.class);

    }
}
