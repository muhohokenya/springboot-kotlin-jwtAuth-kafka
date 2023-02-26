package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.services.IssTrackingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/kafka/iss-tracks")
class IssTrackController(
    val issTrackingService: IssTrackingService
) {


    @GetMapping("")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun getAllIssTracks():ResponseEntity<Any>{
        return ResponseEntity.ok(issTrackingService.getAll())
    }

    @GetMapping("/last")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun getAllLast():ResponseEntity<Any>{
        return ResponseEntity.ok(issTrackingService.getLastRecord())
    }
}