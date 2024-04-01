package org.example.service;

import org.example.domain.Todo;
import org.example.repository.TodoRepository;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class TodoServiceTest {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void testMethod(){

       String input= "11.11.2023";
        LocalDate startDate = LocalDate.parse(input, dateFormatter);

    }


}


