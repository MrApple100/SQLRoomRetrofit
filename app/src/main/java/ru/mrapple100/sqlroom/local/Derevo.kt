package ru.mrapple100.sqlroom.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Derevo")//можно не указывать если совпадает c названием класса
data class Derevo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val material: String,
    //@ColumnInfo(name = "rub")
    val price: Float,
    //val Factory// тоже entity
    val phraseId: Int
)