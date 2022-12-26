package com.justagg.app.di

import android.content.Context
import com.justagg.app.data.network.Api
import com.justagg.app.data.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthApi(
        @ApplicationContext context: Context,
        remoteDataSource: RemoteDataSource
    ): Api {
        return remoteDataSource.buildApi(Api::class.java, context)
    }

}