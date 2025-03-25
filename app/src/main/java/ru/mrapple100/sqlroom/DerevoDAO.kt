package ru.mrapple100.sqlroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DerevoDAO {

    @Query("SELECT * FROM Derevo ORDER by price")//сортировка по цене
    fun getAllDerevo(): List<Derevo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDerevo(derevo: Derevo): Unit

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDerevo(derevo: Derevo): Unit

    @Delete
    fun deleteDerevo(derevo: Derevo): Unit


}