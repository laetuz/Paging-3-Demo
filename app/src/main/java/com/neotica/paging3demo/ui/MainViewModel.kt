package com.neotica.paging3demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.neotica.paging3demo.data.QuoteRepo
import com.neotica.paging3demo.network.QuoteResponseItem
import kotlinx.coroutines.flow.Flow

class MainViewModel(quoteRepo: QuoteRepo) : ViewModel() {
    val quote: Flow<PagingData<QuoteResponseItem>> = quoteRepo.getQuote().cachedIn(viewModelScope)
}