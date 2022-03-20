package com.world.jteam.fixperiodval

import android.app.Application
import androidx.room.Room
import com.world.jteam.fixperiodval.room.AppDatabase

class App: Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this

        instance.db = Room.databaseBuilder(
            instance,
            AppDatabase::class.java, "fixperiodvaldb"
        ).build()
    }

    companion object {
        lateinit var instance: App
            private set
    }


}