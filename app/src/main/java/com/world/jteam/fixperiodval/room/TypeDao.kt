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
    fun insetIndicators(indicators: List<TypeIndicator>)

    @Delete
    fun deleteIndicators(indicators: List<TypeIndicator>)

    @Query("DELETE FROM TypeIndicator WHERE typeId = :typeId")
    fun deleteIndicatorsByTypeId(typeId: Int)

    //basic
    @Transaction
    fun insertType(typeObject: TypeObject){
        val typeId = insert(typeObject.type).toInt()
        typeObject.typeIndicators.forEach {it.typeId = typeId}
        insetIndicators(typeObject.typeIndicators)
    }

    @Transaction
    fun updateType(typeObject: TypeObject){
        update(typeObject.type)
        deleteIndicatorsByTypeId(typeObject.type.id)
        insetIndicators(typeObject.typeIndicators)
    }

    @Transaction
    fun deleteType(typeObject: TypeObject){
        deleteIndicatorsByTypeId(typeObject.type.id)
        delete(typeObject.type)
    }

    @Query("SELECT * FROM Type")
    fun getAllType(): List<Type>

    @Query("SELECT * FROM Type WHERE id = :typeId")
    fun typeObjectById(typeId: Int): TypeObject
}