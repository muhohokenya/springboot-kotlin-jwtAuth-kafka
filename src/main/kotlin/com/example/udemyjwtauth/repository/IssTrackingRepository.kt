package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.IssTracking
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IssTrackingRepository:JpaRepository<IssTracking,Long> {

    fun findFirstByOrderByTimestampDesc(): Optional<IssTracking>
}