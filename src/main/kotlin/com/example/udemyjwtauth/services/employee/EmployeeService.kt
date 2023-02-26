package com.example.udemyjwtauth.services.employee

import com.example.udemyjwtauth.dto.EmployeeDto
import com.example.udemyjwtauth.entity.Employee
import com.example.udemyjwtauth.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private var employeeRepository: EmployeeRepository
) {
    fun saveEmployee(employeeDto: EmployeeDto): Employee {
        val employee = Employee(
            null,
            employeeDto.name,
            employeeDto.age,
            employeeDto.address
        )
         employee.address = employeeDto.address
        return employeeRepository.save(employee)
    }
}