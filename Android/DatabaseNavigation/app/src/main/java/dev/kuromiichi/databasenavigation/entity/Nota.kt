package dev.kuromiichi.databasenavigation.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Nota(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image: String = "https://icons.veryicon.com/png/o/miscellaneous/basic-icon-1/unknown-18.png",
    val title: String = "",
    val content: String = ""
)
