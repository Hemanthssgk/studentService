package com.sms.studentService.studentService.repository;

import com.sms.studentService.studentService.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    public Optional<Student> findByEmail(String email);
    public Optional<Student> findByRollNumber(Integer rollNumber);

}
