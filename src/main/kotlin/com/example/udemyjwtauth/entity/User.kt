package com.example.udemyjwtauth.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,

    @Column(name = "email", nullable = false)
    val email:String,

    @Column(name = "name", nullable = false, unique = true)
    val name:String,

    @Column(name = "password", nullable = false)
    val password:String,

    @Column(name = "username", nullable = false)
    val username:String,
) {
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST]
    )
    @JsonIgnoreProperties("users")
    var roles:MutableList<Role> = mutableListOf()
    constructor() : this(1,"","","","")


}