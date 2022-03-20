package com.world.jteam.fixperiodval.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Indicator(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val unit: String
)
