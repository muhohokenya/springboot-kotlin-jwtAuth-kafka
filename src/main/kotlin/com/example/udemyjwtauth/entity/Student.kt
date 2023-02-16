package com.example.udemyjwtauth.entity

import jakarta.persistence.*


@Table(
    name = "students"
)
@Entity
open class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id:Long?,

    @Column(name = "name", nullable = false)
   open var name:String,

    @Column(name = "admNo", nullable = false, unique = true)
    open var admNo:String,

    @Column(name = "age", nullable = false)
   open var age:String,
) {

    @ManyToMany
    @JoinTable(
        name = "guardian_students",
        joinColumns = [JoinColumn(name = "guardian_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    open var guardian:MutableList<Guardian>? = null
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guardian_id", nullable = false)
    open lateinit var guardian: Guardian*/
    constructor() : this(1,"","","")
}
