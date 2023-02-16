package com.example.udemyjwtauth.service

import com.example.udemyjwtauth.dto.GuardianDto
import com.example.udemyjwtauth.entity.Guardian
import com.example.udemyjwtauth.repository.GuardianRepository
import com.example.udemyjwtauth.service.interfaces.GuardianServiceInterface
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.SQLIntegrityConstraintViolationException


@Service
class GuardianService(
    private val guardianRepository: GuardianRepository
):GuardianServiceInterface {

    fun getAllGuardians():MutableList<Guardian>{
       return guardianRepository.findAll()

    }
    override fun createGuardian(guardianDto: GuardianDto): Guardian {
        return try {
            val guardian = Guardian(
                null,
                guardianDto.name,
                guardianDto.contacts,
            )
            guardianRepository.save(guardian)
        }catch (ex:SQLIntegrityConstraintViolationException){
            println(ex)
            Guardian(1,"","");
        }
    }

    override fun updateGuardian(guardianDto: GuardianDto) {
        TODO("Not yet implemented")
    }

    override fun deleteGuardian(guardianDto: GuardianDto) {
        TODO("Not yet implemented")
    }
}