package com.world.jteam.fixperiodval.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Type(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?
)
