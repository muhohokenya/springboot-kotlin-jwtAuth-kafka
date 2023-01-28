package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository:JpaRepository<Student,Long> {
}