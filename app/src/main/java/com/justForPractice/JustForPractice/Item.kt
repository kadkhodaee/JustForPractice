package com.justForPractice.JustForPractice

import androidx.room.*

@Entity(tableName = "MyItems")
class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "did") var id: Long = 0,
    @ColumnInfo(name = "Title")val Title:String,
    @ColumnInfo(name = "Description")val Description:String,
    @ColumnInfo(name = "Data")val Data:String)