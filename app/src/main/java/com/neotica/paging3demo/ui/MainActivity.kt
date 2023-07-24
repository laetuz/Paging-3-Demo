package com.neotica.paging3demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.neotica.paging3demo.databinding.ActivityMainBinding
import com.neotica.paging3demo.ui.adapter.LoadingStateAdapter
import com.neotica.paging3demo.ui.adapter.QuoteListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvQuote.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData() {
        val adapter = QuoteListAdapter()
        val cScope = CoroutineScope(Dispatchers.Default)
        binding.rvQuote.adapter = adapter.withLoadStateFooter(LoadingStateAdapter{adapter.retry()})
        cScope.launch {
            viewModel.quote.collect {adapter.submitData(lifecycle, it)}
        }
    }
}