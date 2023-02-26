package com.example.udemyjwtauth.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Table(
    name = "address"
)
@Entity
open class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id:Long?,

    @Column(name = "addressName")
    open val addressName:String,

    @Column(name = "city")
    open val city:String,

    @OneToOne(mappedBy = "address")
    open val employee: Employee?
){
    constructor() : this(1,"","",null) {

    }

}
