package com.justForPractice.JustForPractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Item::class], version = 1)
@TypeConverters(Converters::class)
public abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {

        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): ItemRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ItemRoomDatabase::class.java,
                    "MyDatabase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

