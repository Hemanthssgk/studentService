package com.sms.studentService.studentService.controller;

import com.sms.studentService.studentService.Exception.ApiResponse;
import com.sms.studentService.studentService.Exception.NoSuchResourceException;
import com.sms.studentService.studentService.model.Student;
import com.sms.studentService.studentService.model.StudentDto;
import com.sms.studentService.studentService.service.StudentService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping(("/student"))
public class StudentController {
    @Autowired
    StudentService studentService;

    /*
    query parameter
    http body
    path variable
     */
    @PostMapping("/")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody Student student)
    {
        StudentDto studentDto =studentService.saveStudent(student);
        return ResponseEntity.ok(studentDto);
    }

    // this will create a file inside a container. mount that folder to host machine directory
    // then file is persisted on to host machien
    @PostMapping("/createFile")
    public String createfile()
    {
        File file = new File("/home/hema.txt");
        boolean status;
        try {
          status  = file.createNewFile();
            if (status)
            {
                return "created";
            }
            else
            {
                return ("Failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer id) throws NoSuchResourceException {
        if (studentService.deleteStudent(id))
        {
            return ResponseEntity.ok(new ApiResponse(id+" is deleted successfully",new Date()));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No Resource with id "+id+" found",new Date()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody String city,@PathVariable Integer id) throws NoSuchResourceException {
          StudentDto studentDto =studentService.updateStudentCity(id,city);
       return ResponseEntity.ok(studentDto);
    }

    @GetMapping("/byRollNo/{id}")
    public ResponseEntity<StudentDto> findByRollNumber(@PathVariable Integer id) throws NoSuchResourceException {
       StudentDto studentDto = studentService.getStudentByRollNumber(id);
       return ResponseEntity.ok(studentDto);
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<StudentDto> findByEmail(@PathVariable @Email String email) throws NoSuchResourceException {
        StudentDto studentDto = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(studentDto);
    }


}
