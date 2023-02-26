package com.example.udemyjwtauth.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "customers")
@Entity
open class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id:Long?,
    @Column(name = "name")
    open var name:String = "",


    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    open var orders:MutableList<Order> = mutableListOf()
) {

    constructor() : this(1,"", mutableListOf())
}
