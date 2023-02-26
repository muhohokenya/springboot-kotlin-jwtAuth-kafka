package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.EarthQuake
import org.springframework.data.jpa.repository.JpaRepository

interface EarthQuakeRepository:JpaRepository<EarthQuake,Long> {
}