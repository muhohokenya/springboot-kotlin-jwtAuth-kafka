package com.example.udemyjwtauth.service

import com.example.udemyjwtauth.dto.StudentDto
import com.example.udemyjwtauth.entity.Student
import com.example.udemyjwtauth.repository.StudentRepository
import com.example.udemyjwtauth.service.interfaces.StudentServiceInterface
import org.springframework.stereotype.Service

@Service
class StudentService(
    val studentRepository: StudentRepository
):StudentServiceInterface {

    fun getAllStudents(): MutableList<Student> {
        return studentRepository.findAll()
    }


    fun createStudent(student: Student):Student{
        return studentRepository.save(student)
    }


    fun getStudentById(id:Long): Student? {
        return studentRepository.findById(id).orElseThrow { error("Student Not Found") }
    }

    private fun mapToDto(student: Student): StudentDto {
        return StudentDto(
            student.id,
            student.name,
            student.age,
            student.admNo,
            student.guardian,
        )
    }

    override fun updateStudent(studentDto: StudentDto,id: Long):StudentDto {
        val student = Student(
            id,
            studentDto.name,
            studentDto.admNo,
            studentDto.age,
        )
        studentRepository.save(student)
        return mapToDto(student)
    }
}