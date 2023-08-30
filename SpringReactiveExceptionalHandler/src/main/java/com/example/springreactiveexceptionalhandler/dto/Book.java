package com.example.springreactiveexceptionalhandler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private int id;
    private String name;
    private double price;
}
