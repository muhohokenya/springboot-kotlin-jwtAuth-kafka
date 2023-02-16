package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.StudentDto
import com.example.udemyjwtauth.entity.Student
import com.example.udemyjwtauth.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/students")
class StudentsController(
    val studentService: StudentService,
) {
//    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    fun students():MutableList<Student>{
        return studentService.getAllStudents()
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long): Student? {
        return studentService.getStudentById(id)
    }
    @PostMapping("save")
    fun createStudent(@RequestBody studentDto: StudentDto): ResponseEntity<Student> {
        val student = Student(
            null,
            studentDto.name,
            studentDto.admNo,
            studentDto.age
        )

        student.guardian?.add(studentDto.guardian)
        return ResponseEntity(studentService.createStudent(student), HttpStatus.CREATED)
    }

    /*@PutMapping("update/{id}")
    fun updateStudent(
        @RequestBody studentDto: StudentDto,
        @PathVariable id: Long):ResponseEntity<Any>{
        return ResponseEntity.ok(studentService.updateStudent(studentDto,id))
    }*/
    /*private fun mapToDto(student: Student): StudentDto {
        return StudentDto(
            student.id,
            student.name,
            student.age,
            student.admNo,
            student.guardian
        )
    }*/
}