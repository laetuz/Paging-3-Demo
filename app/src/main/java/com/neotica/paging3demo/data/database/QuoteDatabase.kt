package com.neotica.paging3demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neotica.paging3demo.network.QuoteResponseItem

@Database(
    entities = [QuoteResponseItem::class],
    version = 1,
    exportSchema = false
)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

}