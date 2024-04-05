package com.web.login.service.impl;

import com.web.login.dto.EmployeeDTO;
import com.web.login.dto.LoginDTO;
import com.web.login.entity.Employee;
import com.web.login.response.LoginMessage;
import com.web.login.repository.EmployeeRepository;
import com.web.login.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {

        Employee employee = new Employee(
                employeeDTO.getEmployeeId(),
                employeeDTO.getEmployeeName(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword())
        );

        employeeRepository.save(employee);

        return employee.getEmployeeName();
    }

    @Override
    public LoginMessage loginEmployee(LoginDTO loginDTO) {

        Employee employee1 = employeeRepository.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Employee> employee = employeeRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }

    }


}
