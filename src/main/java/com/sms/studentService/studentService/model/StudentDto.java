package com.sms.studentService.studentService.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    Integer id;
    String name;
    Integer yearOfJoining;
    String email;
    String city;
}
