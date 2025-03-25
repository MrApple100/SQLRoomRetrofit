package ru.mrapple100.sqlroom

import androidx.room.Room
import ru.mrapple100.sqlroom.local.AppDataAplication

object LocalDI {

    var db: AppDataAplication = Room.databaseBuilder(
        MainActivity.applicationContext(),
        AppDataAplication::class.java,"DerevoWithPhrase5"
    ).build()

}