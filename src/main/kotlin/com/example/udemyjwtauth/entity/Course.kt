package com.example.udemyjwtauth.entity

import jakarta.persistence.*


@Entity
@Table(name = "courses")
data class Course (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
     var id: Long? = null,

    @Column(name = "name", nullable = false)
     var name:String,

    @ManyToMany(mappedBy = "courses")
     val students: MutableList<Student>? = null
){



    constructor() : this(1,"")

}