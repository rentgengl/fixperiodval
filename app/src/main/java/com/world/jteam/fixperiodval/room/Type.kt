package com.world.jteam.fixperiodval.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Type(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String?
)

@Entity
data class TypeIndicator(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var typeId: Int?,
    var indicatorId: Int?
)

data class TypeObject(
    @Embedded val type: Type,
    @Relation(
        parentColumn = "id",
        entityColumn = "typeId"
    )
    val typeIndicators: MutableList<TypeIndicator>
)
