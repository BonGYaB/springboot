package com.seansen.dev.demoSpringBoot.student;

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
        return args -> {
            Student sean = new Student(
                    "Sean Student Service",
                    "sean@gmail.com",
                    LocalDate.of(1999, Month.FEBRUARY, 2)
            );

            Student sen = new Student(
                    "Sen Student Service",
                    "sen@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student sem = new Student(
                    "Sem Student Service",
                    "sem@gmail.com",
                    LocalDate.of(2001, Month.DECEMBER, 3)
            );

            repository.saveAll(
                List.of(sean, sen, sem)
            );
        };
    }
}
