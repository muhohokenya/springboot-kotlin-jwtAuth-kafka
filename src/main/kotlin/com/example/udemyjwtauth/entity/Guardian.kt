package com.example.udemyjwtauth.entity

import jakarta.persistence.*


@Entity
@Table(name = "guardians")
open class Guardian (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    open var id: Long? = null,

    @Column(name = "name", nullable = false)
    open var name:String,

    @Column(name = "contacts", nullable = false, unique = true)
    open var contacts:String,


    @ManyToMany(mappedBy = "guardian")
    open val students: MutableList<Student>? = null
){



    constructor() : this(1,"","",null)

}