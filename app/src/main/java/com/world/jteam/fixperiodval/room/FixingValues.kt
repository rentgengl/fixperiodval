package com.world.jteam.fixperiodval.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class FixingValues(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var fixingId: Int,
    var indicatorId: Int,
    var value: Long,
    @Ignore val unit: String
)
