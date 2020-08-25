package com.justForPractice.JustForPractice


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData



public class ItemRepository(private val itemDao: ItemDao){

    val allitems: LiveData<List<Item>> = itemDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: Item){
        itemDao.insert(item)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(){
        itemDao.deleteAll()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(item: Item){
        itemDao.delete(item)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(item: Item){
        itemDao.update(item)
    }
}
