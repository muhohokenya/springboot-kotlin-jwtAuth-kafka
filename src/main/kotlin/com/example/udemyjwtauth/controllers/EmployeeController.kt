package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.EmployeeDto
import com.example.udemyjwtauth.entity.Address
import com.example.udemyjwtauth.entity.Employee
import com.example.udemyjwtauth.services.employee.EmployeeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/employees/v1")
class EmployeeController(
    val employeeService: EmployeeService
) {


    @PostMapping("save")
    fun save(@RequestBody employeeDto: EmployeeDto): Employee {
        return employeeService.saveEmployee(employeeDto)
    }


}