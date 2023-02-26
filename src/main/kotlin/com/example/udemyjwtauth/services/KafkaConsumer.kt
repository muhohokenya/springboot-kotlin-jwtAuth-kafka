package com.example.udemyjwtauth.services

import com.example.udemyjwtauth.dto.EarthQuakeDTO
import com.example.udemyjwtauth.dto.IssDTO
import com.example.udemyjwtauth.services.EarthQuakeService
import com.example.udemyjwtauth.services.IssTrackingService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Service
class KafkaConsumer(
    val earthQuakeService: EarthQuakeService,
    val issTrackingService: IssTrackingService
) {

    @KafkaListener(topics = ["earthQuakeTopic"], groupId = "myGroup")
    fun consume(earthQuakeDTO: EarthQuakeDTO){
         earthQuakeService.createEarthQuake(earthQuakeDTO)
    }


    @KafkaListener(topics = ["issTopic"], groupId = "myGroup")
    fun readIssTrackingRecords(issDTO: IssDTO){
        issTrackingService.createIssTrackingRecord(issDTO)
    }
}