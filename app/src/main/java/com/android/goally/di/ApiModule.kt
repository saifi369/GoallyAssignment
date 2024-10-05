package com.getgoally.learnerapp.di

import com.android.goally.data.network.rest.api.CopilotApi
import com.android.goally.data.network.rest.api.GeneralApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGeneralApi(retrofit: Retrofit.Builder): GeneralApi {
        return retrofit.build().create(GeneralApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCopilotApi(retrofit: Retrofit.Builder): CopilotApi {
        return retrofit.build().create(CopilotApi::class.java)
    }

}