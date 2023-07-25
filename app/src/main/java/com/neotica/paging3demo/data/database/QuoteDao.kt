package com.neotica.paging3demo.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neotica.paging3demo.network.QuoteResponseItem

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: List<QuoteResponseItem>)

    @Query("select * from quote")
    fun getAllQuote(): PagingSource<Int, QuoteResponseItem>

    @Query("delete from quote")
    fun deleteAll()
}