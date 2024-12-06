package com.app.backend.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return  args -> {
            Student Hien = new Student(
                    "Hien",
                    "hien@gmail.com",
                    LocalDate.of(2004, Month.AUGUST, 15));

            Student Ha = new Student(
                    "Ha",
                    "ha@gmail.com",
                    LocalDate.of(2002, Month.AUGUST, 15));

            Student Truc = new Student(
                    "Truc",
                    "truc@gmail.com",
                    LocalDate.of(2003, Month.NOVEMBER, 6));

            repository.saveAll(List.of(Hien, Ha, Truc));
        };
    }

}
