package com.justForPractice.JustForPractice


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * from MyItems ")
    fun getAll() : LiveData<List<Item>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Item)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(item: Item)

    @Query("DELETE FROM MyItems")
    fun deleteAll()

    @Delete
    fun delete(item: Item)
}
