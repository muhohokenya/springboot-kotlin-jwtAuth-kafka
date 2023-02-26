package com.example.udemyjwtauth.entity

import jakarta.persistence.*

@Table(
    name = "earth_quakes"
)
@Entity
open class EarthQuake(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id:Long?,

    @Column(name = "mag", nullable = false)
    open var mag:String,

    @Column(name = "place", nullable = false, unique = false)
    open var place:String,
){
    constructor() : this(1,"","")
}