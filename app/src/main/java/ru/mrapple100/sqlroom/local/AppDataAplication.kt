package ru.mrapple100.sqlroom.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mrapple100.sqlroom.local.Derevo
import ru.mrapple100.sqlroom.local.DerevoDAO

@Database(entities = [Derevo::class,Phrase::class], version = 1)
abstract class AppDataAplication: RoomDatabase() {
    abstract fun derevoDAO(): DerevoDAO
}