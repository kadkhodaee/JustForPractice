package com.justForPractice.JustForPractice

import androidx.room.*
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "MyItems")
class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "did") var id: Long = 0,
    @ColumnInfo(name = "Title") val Title:String,
    @ColumnInfo(name = "Description") val Description:String,
    @ColumnInfo(name = "Date")
    @TypeConverters(Converters::class) val Date: Date
)

public class Converters{
    private val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm")

    @TypeConverter
    fun dateToTimestamp(value:String): Date? {
            return formatter.parse(value)
    }
    @TypeConverter
    fun fromTimestamp(date:Date): String? {
        return formatter.format(date).toString()
    }

}