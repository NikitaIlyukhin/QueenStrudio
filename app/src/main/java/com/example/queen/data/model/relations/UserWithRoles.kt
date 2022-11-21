package com.example.queen.data.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.queen.data.model.entity.Role
import com.example.queen.data.model.entity.User
import com.example.queen.data.model.entity.UserRoles

data class UserWithRoles (
    @Embedded val user: User,
    @Relation(
            parentColumn = "id",
            entityColumn = "role_name",
            associateBy = Junction(UserRoles::class)
        )
        val roles:List<Role>
    )