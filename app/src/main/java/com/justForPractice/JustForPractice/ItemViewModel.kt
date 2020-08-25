package com.justForPractice.JustForPractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository:ItemRepository
    val allitems: LiveData<List<Item>>
    init {
        val itemDao = ItemRoomDatabase.getDatabase(application, viewModelScope).itemDao()
        repository = ItemRepository(itemDao)
        allitems = repository.allitems
    }
    fun insert(item: Item)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
    fun deleteAll()=viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }
    fun delete(item: Item)=viewModelScope.launch(Dispatchers.IO){
        repository.delete(item)
    }
    fun update(item: Item)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(item)
    }
}