package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository:JpaRepository<Employee,Long> {

}