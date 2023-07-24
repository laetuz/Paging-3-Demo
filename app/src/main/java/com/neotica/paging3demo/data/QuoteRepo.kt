package com.neotica.paging3demo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.neotica.paging3demo.data.database.QuoteDatabase
import com.neotica.paging3demo.network.ApiService
import com.neotica.paging3demo.network.QuoteResponseItem
import kotlinx.coroutines.flow.Flow

class QuoteRepo(private val quoteDatabase: QuoteDatabase, private val apiService: ApiService) {
    fun getQuote(): Flow<PagingData<QuoteResponseItem>> {
        return Pager(
            config = PagingConfig(5),
            pagingSourceFactory = { QuotePagingSource(apiService) }
        ).flow
    }
}