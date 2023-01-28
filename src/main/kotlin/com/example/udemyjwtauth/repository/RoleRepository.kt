package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository:JpaRepository<Role,Long> {
    fun findRoleByName(name:String):Optional<Role>
}