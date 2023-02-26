package com.example.udemyjwtauth.controllers.kafka

import com.example.udemyjwtauth.services.KafkaConsumer
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/kafka/consume")
class KafkaConsumerController(
    val kafkaConsumer: KafkaConsumer
) {


    /*@GetMapping("")
    fun readTopics():ResponseEntity<EarthQuakeDTO>{
        val earthQuakeDTO = EarthQuakeDTO("","")
        return ResponseEntity.ok(kafkaConsumer.consume(earthQuakeDTO))
    }*/
}