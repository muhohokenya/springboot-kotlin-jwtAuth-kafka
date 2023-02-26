package com.example.udemyjwtauth.services

import com.example.udemyjwtauth.dto.EarthQuakeDTO
import com.example.udemyjwtauth.entity.EarthQuake
import com.example.udemyjwtauth.repository.EarthQuakeRepository
import org.springframework.stereotype.Service

@Service
class EarthQuakeService(
    val earthQuakeRepository: EarthQuakeRepository
) {

    fun createEarthQuake(earthQuakeDTO: EarthQuakeDTO): EarthQuake {
        val earthQuake = EarthQuake(
            earthQuakeDTO.id,
            earthQuakeDTO.mag,
            earthQuakeDTO.place
        )
        return earthQuakeRepository.save(earthQuake)
    }
}