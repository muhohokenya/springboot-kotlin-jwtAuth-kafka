package com.example.udemyjwtauth.entity

import jakarta.persistence.*

@Entity
@Table(name = "orders")
open class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id:Long?,
    @Column(name = "name")
    open var name:String
) {
    constructor() : this(1,"")
}