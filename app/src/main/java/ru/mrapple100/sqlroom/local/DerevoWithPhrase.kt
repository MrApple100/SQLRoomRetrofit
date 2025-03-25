package ru.mrapple100.sqlroom.local

import androidx.room.Embedded
import androidx.room.Relation

data class DerevoWithPhrase(
    @Embedded
    val derevo: Derevo,
    @Relation(
        parentColumn = "phraseId",
        entityColumn = "phraseId"
    )
    val phrase: Phrase
)