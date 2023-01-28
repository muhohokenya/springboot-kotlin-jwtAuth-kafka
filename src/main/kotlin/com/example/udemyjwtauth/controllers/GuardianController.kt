package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.GuardianDto
import com.example.udemyjwtauth.service.GuardianService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/guardians")
class GuardianController(
    private val guardianService: GuardianService
) {


    @PostMapping("/save")
    fun createGuardian(@RequestBody guardianDto: GuardianDto):ResponseEntity<Any>{
        guardianService.createGuardian(guardianDto)
        return ResponseEntity.ok("success")
    }
}