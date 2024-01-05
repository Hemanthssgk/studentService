package com.sms.studentService.studentService.service;

import com.sms.studentService.studentService.Exception.NoSuchResourceException;
import com.sms.studentService.studentService.model.Student;
import com.sms.studentService.studentService.model.StudentDto;
import com.sms.studentService.studentService.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


@Component
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentDto saveStudent(@RequestBody Student student)
    {
        studentRepository.save(student);
        return mapStudentToDto(student);
    }


    public boolean deleteStudent(Integer id) throws NoSuchResourceException {
        if (id!=null&&studentRepository.existsById(id))
        {
            studentRepository.deleteById(id);
            return true;
        }
        throw  new NoSuchResourceException("No Resource of id "+id+" found");
    }

    public StudentDto updateStudentCity(Integer id, String city) throws NoSuchResourceException {
        Student student = studentRepository.findByRollNumber(id).orElseThrow(()-> new NoSuchResourceException("No Resource of id "+id+" found"));

        student.setCity(city);
        studentRepository.save(student);
        return mapStudentToDto(student);

    }

    public StudentDto getStudentByRollNumber(Integer id) throws NoSuchResourceException {
        Student student = studentRepository.findByRollNumber(id).orElseThrow(()-> new NoSuchResourceException("No Resource of id "+id+" found"));
        if (student==null)
        {
            throw new NoSuchResourceException("No Resource with id "+id+" found");
        }
        return mapStudentToDto(student);
    }
    public StudentDto getStudentByEmail(String email) throws NoSuchResourceException {
        Student student = studentRepository.findByEmail(email).orElseThrow(()-> new NoSuchResourceException("No Resource with email "+email+" found"));
        return mapStudentToDto(student);
    }

    public StudentDto mapStudentToDto(Student student)
    {
        StudentDto studentDto = new StudentDto();
        studentDto.setCity(student.getCity());
        studentDto.setId(student.getRollNumber());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setYearOfJoining(student.getYearOfJoining());
        return studentDto;
    }
}
