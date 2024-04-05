package com.web.login.controller;

import com.web.login.dto.EmployeeDTO;
import com.web.login.dto.LoginDTO;
import com.web.login.response.LoginMessage;
import com.web.login.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        String id = employeeService.addEmployee(employeeDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO){
        LoginMessage loginMessage = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

}
