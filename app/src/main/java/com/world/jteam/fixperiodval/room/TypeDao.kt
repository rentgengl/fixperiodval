package com.world.jteam.fixperiodval.room

import androidx.room.*

@Dao
interface TypeDao {
    //Type
    @Insert
    fun insert(type: Type): Long

    @Delete
    fun delete(type: Type)

    @Update
    fun update(type: Type)

    //TypeIndicators
    @Insert
    fun insetIndicators(indicators: List<TypeIndicators>)

    @Delete
    fun deleteIndicators(indicators: List<TypeIndicators>)

    @Query("SELECT * FROM TypeIndicators WHERE typeId = :typeId")
    fun selectTypeIndicatorsByType(typeId: Int): List<TypeIndicators>

    //basic
    @Transaction
    fun insertType(type: Type, indicators: List<TypeIndicators>){
        val typeId = insert(type).toInt()

        indicators.forEach {it.typeId = typeId}

        insetIndicators(indicators)
    }

    @Transaction
    fun updateType(type: Type, indicators: List<TypeIndicators>){
        update(type)

        val indicatorsOld = selectTypeIndicatorsByType(type.id)
        deleteIndicators(indicatorsOld)

        insetIndicators(indicators)
    }

    @Transaction
    fun deleteType(type: Type){
        val indicators = selectTypeIndicatorsByType(type.id)
        deleteIndicators(indicators)
        delete(type)
    }
}