package com.example.udemyjwtauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class UdemyJwtAuthApplication
//    val kafkaProducer: KafkaProducer

//    override fun run(vararg args: String?) {
//        kafkaProducer.senMessageViaEvent()
//    }


fun main(args: Array<String>) {
    runApplication<UdemyJwtAuthApplication>(*args)
}

