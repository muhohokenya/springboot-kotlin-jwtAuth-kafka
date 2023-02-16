package com.example.udemyjwtauth.controllers.kafka

import com.example.udemyjwtauth.dto.KafkaRequest
import com.example.udemyjwtauth.kafka.KafkaProducer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/kafka")
class KafkaProducerController(
    val kafkaProducer: KafkaProducer
) {


    @PostMapping("send")
    fun sendMessageToTopic(
        @RequestBody kafkaRequest: KafkaRequest
    ):ResponseEntity<Any>{
        kafkaProducer.sendMessage(kafkaRequest)
        return ResponseEntity.ok("Message Sent")
    }
}