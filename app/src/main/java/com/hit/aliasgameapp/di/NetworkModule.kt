package com.hit.aliasgameapp.di

import com.hit.aliasgameapp.data.remote.ImageApi
import com.hit.aliasgameapp.data.remote.NameApi
import com.hit.aliasgameapp.data.remote.RandomWordApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://random-word-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRandomWordApi(retrofit: Retrofit): RandomWordApi {
        return retrofit.create(RandomWordApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNameApi(): NameApi {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NameApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageApi(): ImageApi {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .build()
            .create(ImageApi::class.java)
    }
}