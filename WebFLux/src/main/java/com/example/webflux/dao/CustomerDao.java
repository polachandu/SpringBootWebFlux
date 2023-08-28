package com.example.webflux.dao;

import com.example.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {


    private static IntConsumer sleepExecution(int i)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::sleepExecution)
                .peek(i ->System.out.println("Processing count: "+i))
                .mapToObj(i -> new Customer(i,"Customer "+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersFluxWithDelay() {
        return Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i ->System.out.println("Processing count: "+i))
                .map(i -> new Customer(i, "Customer "+i));

    }

    public Flux<Customer> getCustomersFlux() {
        return Flux.range(1,50)
                .doOnNext(i ->System.out.println("Processing count: "+i))
                .map(i -> new Customer(i, "Customer "+i));

    }
}
