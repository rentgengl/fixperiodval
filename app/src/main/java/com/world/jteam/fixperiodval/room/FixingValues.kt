package com.world.jteam.fixperiodval.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class FixingValues {
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1
    var fixingId: Int? = null
    var indicatorId: Int? = null
    var value: Long? = null
    @Ignore
    var unit: String? = null
}
