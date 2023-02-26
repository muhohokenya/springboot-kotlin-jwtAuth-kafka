package com.example.udemyjwtauth.entity

import jakarta.persistence.*

@Table(
    name = "employees"
)
@Entity
open class Employee(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id:Long?,

    @Column(name = "name", nullable = false)
    open var name:String,

    @Column(name = "age", nullable = false, unique = false)
    open var age:Int,


    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    open var address: Address?
){

    constructor():this(1,"",0, null)

}