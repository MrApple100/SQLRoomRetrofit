package ru.mrapple100.sqlroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Derevo::class], version = 1)
abstract class AppDataAplication: RoomDatabase() {
    abstract fun derevoDAO(): DerevoDAO
}