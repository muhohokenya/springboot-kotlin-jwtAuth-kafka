package com.example.udemyjwtauth.service.interfaces

import com.example.udemyjwtauth.dto.StudentDto

interface StudentServiceInterface {

    fun updateStudent(studentDto: StudentDto,id :Long):StudentDto
}