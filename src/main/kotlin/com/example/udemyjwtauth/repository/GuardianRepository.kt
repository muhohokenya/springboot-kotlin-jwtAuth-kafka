package com.example.udemyjwtauth.repository;

import com.example.udemyjwtauth.entity.Guardian
import org.springframework.data.jpa.repository.JpaRepository

interface GuardianRepository : JpaRepository<Guardian, Long> {

}