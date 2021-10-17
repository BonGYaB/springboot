package com.seansen.dev.demoSpringBoot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        System.out.println(student.toString());
        studentRepository.save(student);
//       studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
//        Optional<Student> studentOptional = studentRepository.findStudentById(id);
        boolean studentExist = studentRepository.existsById(studentId);
        if(!studentExist){
            throw new IllegalStateException("Student ID (" +  studentId + ") not found!!!!");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        System.out.println("Update Student: Id=" + studentId + ", name=" + name + ", email=" + email);

        Student studentInfo = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " does not exist"
                ));

        if(name != null
            && name.length() > 0
            && !Objects.equals(studentInfo.getName(), name)) {
            studentInfo.setName(name);
        }

        if(email != null
                && email.length() > 0
                && !Objects.equals(studentInfo.getName(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("Email Taken");
            }
            studentInfo.setEmail(email);
        }
    }
}
