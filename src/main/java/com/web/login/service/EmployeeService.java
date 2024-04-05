package com.web.login.service;

import com.web.login.dto.EmployeeDTO;
import com.web.login.dto.LoginDTO;
import com.web.login.response.LoginMessage;

public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

    LoginMessage loginEmployee(LoginDTO loginDTO);

}
