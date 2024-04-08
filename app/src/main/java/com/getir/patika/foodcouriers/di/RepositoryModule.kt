package com.getir.patika.foodcouriers.di

import com.getir.patika.foodcouriers.data.remote.ApiService
import com.getir.patika.foodcouriers.domain.repository.FoodRepository
import com.getir.patika.foodcouriers.domain.repository.OrderRepository
import com.getir.patika.foodcouriers.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        apiService: ApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) = UserRepository(apiService,ioDispatcher)

    @Provides
    fun provideFoodRepository(
        apiService: ApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) = FoodRepository(apiService,ioDispatcher)


    @Provides
    fun provideOrderRepository(
        apiService: ApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) = OrderRepository(apiService,ioDispatcher)





}