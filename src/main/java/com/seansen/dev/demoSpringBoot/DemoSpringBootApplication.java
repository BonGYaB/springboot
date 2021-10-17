package com.seansen.dev.demoSpringBoot;

import com.seansen.dev.demoSpringBoot.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}

	@GetMapping
	public List<Student> hello() {
//		return List.of("Hello World", "World");
		return List.of(
				new Student(
						1L,
						"Sean",
						"sean@gmail.com",
						LocalDate.of(2021, Month.JANUARY, 5),
						28
				)
		);
	}
}
