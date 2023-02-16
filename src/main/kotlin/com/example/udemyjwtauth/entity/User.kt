package com.example.udemyjwtauth.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "users")
open class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id:Long,


    @Column(name = "email", nullable = false)
    open val email:String,

    @Column(name = "name", nullable = false, unique = true)
    open val name:String,

    @NotNull
    @Column(name = "password", nullable = false)
    open val password:String,

    @NotNull
    @Column(name = "username", nullable = false)
    open val username:String,
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
    open var roles:MutableList<Role> = mutableListOf()
    constructor() : this(1,"","","","")


}