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

    @Query("DELETE FROM TypeIndicators WHERE typeId = :typeId")
    fun deleteIndicatorsByTypeId(typeId: Int)

    //basic
    @Transaction
    fun insertType(typeObject: TypeObject){
        val typeId = insert(typeObject.type).toInt()
        typeObject.indicators.forEach {it.typeId = typeId}
        insetIndicators(typeObject.indicators)
    }

    @Transaction
    fun updateType(typeObject: TypeObject){
        update(typeObject.type)
        deleteIndicatorsByTypeId(typeObject.type.id)
        insetIndicators(typeObject.indicators)
    }

    @Transaction
    fun deleteType(typeObject: TypeObject){
        deleteIndicatorsByTypeId(typeObject.type.id)
        delete(typeObject.type)
    }

    @Query("SELECT * FROM Type")
    fun getAllType(): List<Type>
}