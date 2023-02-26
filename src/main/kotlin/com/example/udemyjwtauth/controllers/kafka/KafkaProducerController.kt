package com.example.udemyjwtauth.controllers.kafka

import com.example.udemyjwtauth.dto.EarthQuakeDTO
import com.example.udemyjwtauth.dto.IssDTO
import com.example.udemyjwtauth.dto.KafkaRequest
import com.example.udemyjwtauth.services.kafka.KafkaProducer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
@RequestMapping("/api/v1/kafka")
class KafkaProducerController(
    val kafkaProducer: KafkaProducer
) {

    var headers = HttpHeaders()

    @PostMapping("send")
    fun sendMessageToTopic(
        @RequestBody kafkaRequest: KafkaRequest
    ): ResponseEntity<Any> {
        kafkaProducer.sendMessage(kafkaRequest)
        return ResponseEntity.ok("Message Sent")
    }


    @GetMapping("/iss")
    fun startIssTracking():ResponseEntity<Any>{
        val restTemplate = RestTemplate()
        val uri = "http://api.open-notify.org/iss-now.json"
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<String>(headers)
        val result = restTemplate.exchange(uri, HttpMethod.GET, entity, String::class.java)
        val jsonMapper = ObjectMapper()
        val root: JsonNode = jsonMapper.readTree(result.body)
        val issPosition = root.path("iss_position");
        val timestamp = root.path("timestamp");
        val issPositionRoot = jsonMapper.readTree(issPosition.toString())
        val latitude = issPositionRoot.path("latitude")
        val longitude = issPositionRoot.path("longitude")

        val issDto = IssDTO(
            timestamp.toString(),
            latitude.toString(),
            longitude.toString()
        )
        kafkaProducer.broadCast(issDto,"issTopic")
        return ResponseEntity.ok("longitude");
    }




    @PostMapping("earthquake")
    fun earthquakeHttp(): ResponseEntity<Any> {
        val restTemplate = RestTemplate()
        val uri = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2023-01-02"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<String>(headers)
        val result = restTemplate.exchange(uri, HttpMethod.GET, entity, String::class.java)
        val jsonMapper = ObjectMapper()
        val root: JsonNode = jsonMapper.readTree(result.body)
        val features = root.path("features");

        for ((index, item) in features.withIndex()) {
            val featuresRoot = jsonMapper.readTree(item.toString())
            val properties = featuresRoot.path("properties")
            val propertiesRoot = jsonMapper.readTree(properties.toString())
            val mag = propertiesRoot.path("mag")
            val place = propertiesRoot.path("place")

            val earthQuakeDTO = EarthQuakeDTO(
                index.toLong(),
                mag.toString(),
                place.asText()
            )
            kafkaProducer.publishEarthQuake(earthQuakeDTO)
        }
        return ResponseEntity.ok(features)
    }
}