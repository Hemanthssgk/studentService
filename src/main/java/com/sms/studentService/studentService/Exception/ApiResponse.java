package com.sms.studentService.studentService.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ApiResponse {
    String response;
    Date date;
}
