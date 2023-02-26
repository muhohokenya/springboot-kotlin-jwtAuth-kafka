package com.example.udemyjwtauth.schedules

import com.example.udemyjwtauth.dto.IssDTO
import com.example.udemyjwtauth.services.kafka.KafkaProducer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*


@Component
class IssScheduler(
    val kafkaProducer: KafkaProducer
) {


    val headers = HttpHeaders()
    val restTemplate = RestTemplate()

    @Value("\${app.iss-url}")
    val issUrl:String = ""

    @Scheduled(fixedDelay = 1000)
    fun execute(){
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<String>(headers)
        val result = restTemplate.exchange(issUrl,
            HttpMethod.GET, entity, String::class.java)
        val jsonMapper = ObjectMapper()
        val root: JsonNode = jsonMapper.readTree(result.body)
        val issPosition = root.path("iss_position")
        val timestamp = root.path("timestamp")
        val issPositionRoot = jsonMapper.readTree(issPosition.toString())
        val latitude = issPositionRoot.path("latitude")
        val longitude = issPositionRoot.path("longitude")

        val issDto = IssDTO(
            timestamp.toString().replace("\"", ""),
            latitude.toString().replace("\"", ""),
            longitude.toString().replace("\"", "")
        )
        kafkaProducer.broadCast(issDto,"issTopic")
    }

}