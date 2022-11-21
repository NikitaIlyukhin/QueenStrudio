package com.example.queen.data.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.queen.data.model.entity.Role
import com.example.queen.data.model.entity.User
import com.example.queen.data.model.entity.UserRoles

data class RoleWithUsers (
    @Embedded
    val role: Role,
    @Relation(
        parentColumn = "role_name",
        entityColumn = "id",
        associateBy = Junction(UserRoles::class)
    )
    val users:List<User>
)