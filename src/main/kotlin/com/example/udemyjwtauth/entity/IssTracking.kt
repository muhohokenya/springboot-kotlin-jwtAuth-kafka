package com.example.udemyjwtauth.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDate
import java.util.Date

@Table(
    name = "iss_trackings"
)
@Entity
open class IssTracking(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id:Long?,

    @Column(name = "timestamp", nullable = false)
    open var timestamp:LocalDate?,

    @Column(name = "latitude", nullable = false, unique = false)
    open var latitude:String,

    @Column(name = "longitude", nullable = false, unique = false)
    open var longitude:String,
){

    constructor() : this(1,null,"","") {

    }

}