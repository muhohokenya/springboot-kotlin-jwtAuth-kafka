package com.example.udemyjwtauth.services

import com.example.udemyjwtauth.dto.IssDTO
import com.example.udemyjwtauth.entity.IssTracking
import com.example.udemyjwtauth.repository.IssTrackingRepository
import org.springframework.stereotype.Service
import java.sql.Date
import java.sql.Timestamp
import java.util.Optional

@Service
class IssTrackingService(
    val issTrackingRepository: IssTrackingRepository

) {



    fun createIssTrackingRecord(issDTO: IssDTO){
        val stamp = Timestamp(issDTO.timestamp.toLong())
        val date = Date(stamp.time).toLocalDate()
        val issTrackRecord = IssTracking(
            1,
            date,
            issDTO.latitude.removeSurrounding("",""),
            issDTO.longitude.removeSurrounding("",""),
        )
        issTrackingRepository.save(issTrackRecord)
    }


    fun getAll():List<IssTracking>{
        return issTrackingRepository.findAll()
    }


    fun getLastRecord():Optional<IssTracking>{
        return issTrackingRepository.findFirstByOrderByTimestampDesc()
    }
}