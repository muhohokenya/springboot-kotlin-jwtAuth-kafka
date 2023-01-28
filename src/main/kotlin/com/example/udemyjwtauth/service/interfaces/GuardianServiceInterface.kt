package com.example.udemyjwtauth.service.interfaces

import com.example.udemyjwtauth.dto.GuardianDto
import com.example.udemyjwtauth.entity.Guardian

interface GuardianServiceInterface {

    fun createGuardian(guardianDto: GuardianDto): Guardian
    fun updateGuardian(guardianDto: GuardianDto)
    fun deleteGuardian(guardianDto: GuardianDto)

}