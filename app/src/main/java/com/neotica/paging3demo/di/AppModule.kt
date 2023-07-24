package com.neotica.paging3demo.di

import android.content.Context
import androidx.room.Room
import com.neotica.paging3demo.data.QuoteRepo
import com.neotica.paging3demo.data.database.QuoteDatabase
import com.neotica.paging3demo.network.ApiService
import com.neotica.paging3demo.ui.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor).build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://quote-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
val viewModule = module {
    viewModel { MainViewModel(get()) }
    single { QuoteRepo(get(),get()) }
}

val databaseModule = module {
    single { provideDatabase(androidContext()) }
}

private fun provideDatabase(context: Context): QuoteDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        QuoteDatabase::class.java, "quote_database"
    )
        .fallbackToDestructiveMigration()
        .build()
}