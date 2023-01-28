package com.example.udemyjwtauth.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "roles")
open class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    open val id: Long,
    open val name:String
    ){

    @ManyToMany(
        mappedBy = "roles",
        cascade = [CascadeType.PERSIST],
        fetch = FetchType.EAGER
    )

    @JsonIgnoreProperties("roles")
    open var users: MutableList<User> = mutableListOf()
    constructor():this(1,"");
}