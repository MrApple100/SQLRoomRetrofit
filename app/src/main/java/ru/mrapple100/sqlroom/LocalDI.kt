package ru.mrapple100.sqlroom

import android.content.Context
import androidx.room.Room

object LocalDI {

    var db: AppDataAplication = Room.databaseBuilder(
        MainActivity.applicationContext(),
        AppDataAplication::class.java,"Derevo"
    ).build()

}