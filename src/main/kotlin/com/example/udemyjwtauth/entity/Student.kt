package com.example.udemyjwtauth.entity

import jakarta.persistence.*


@Table(
    name = "students",
    uniqueConstraints = [
        UniqueConstraint(
            name = "admNoUnique",
            columnNames = [
                "admNo"
            ]
        )
    ]
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "student_courses",
        joinColumns = [JoinColumn(name = "student_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "course_id", referencedColumnName = "id")]
    )
    open var courses:MutableList<Course>? = null
    constructor() : this(1,"","","")
}
