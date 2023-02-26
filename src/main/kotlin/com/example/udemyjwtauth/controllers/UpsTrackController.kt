package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.UpsResponseDto
import com.example.udemyjwtauth.services.kafka.KafkaProducer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI

@RestController
@RequestMapping("/api/v1/ups")
class UpsTrackController(
    val kafkaProducer: KafkaProducer
) {

    val webClient = WebClient.create()

    private fun transform(response: String?): UpsResponseDto {
        val jsonMapper = ObjectMapper()
        val trackResponse: JsonNode = jsonMapper.readTree(response.toString())

        val trackResponsePath = trackResponse.path("trackResponse")
        val shipment = trackResponsePath.path("shipment")
        val packageArray = arrayOf(shipment).first()
        val packageNodeArray = packageArray.first()

        val array = packageNodeArray.path("package").first()
        val activity = array.path("activity").first()
        val status = activity.path("status")

        return UpsResponseDto(
            status.path("type").toString(),
            status.path("description").toString(),
            status.path("code").toString(),
            status.path("statusCode").toString()
        )
    }

    @PostMapping("")
    fun trackWithUps() {
        val response = webClient.get()
            .uri(URI("https://onlinetools.ups.com/track/v1/details/1ZX20W700390926986"))
            .header("AccessLicenseNumber", "3DC954FBC368D3F1")
            .header("Username", "Leprechaun2")
            .header("Password", "Easy2017")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
        kafkaProducer.publishOnUpsTrackingTopic(transform(response))
    }

}