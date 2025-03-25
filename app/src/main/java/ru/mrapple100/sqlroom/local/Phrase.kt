package ru.mrapple100.sqlroom.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Phrase(
    @PrimaryKey(autoGenerate = true)
    val phraseId: Int,
    val phrase: String
)