package com.getir.patika.foodcouriers.di

import com.getir.patika.foodcouriers.domain.repository.FoodRepository
import com.getir.patika.foodcouriers.domain.repository.OrderRepository
import com.getir.patika.foodcouriers.domain.repository.UserRepository
import com.getir.patika.foodcouriers.domain.usecase.food.CategoriesFoodsUseCase
import com.getir.patika.foodcouriers.domain.usecase.food.FoodsUseCase
import com.getir.patika.foodcouriers.domain.usecase.food.SearchFoodsUseCase
import com.getir.patika.foodcouriers.domain.usecase.order.ActiveOrdersUseCase
import com.getir.patika.foodcouriers.domain.usecase.order.DeleteOrdersByIdUseCase
import com.getir.patika.foodcouriers.domain.usecase.order.DeleteOrdersUseCase
import com.getir.patika.foodcouriers.domain.usecase.order.OrdersCompleteUseCase
import com.getir.patika.foodcouriers.domain.usecase.order.OrdersUseCase
import com.getir.patika.foodcouriers.domain.usecase.order.UpdateOrdersUseCase
import com.getir.patika.foodcouriers.domain.usecase.user.LocationUseCase
import com.getir.patika.foodcouriers.domain.usecase.user.LoginUseCase
import com.getir.patika.foodcouriers.domain.usecase.user.ProfileUseCase
import com.getir.patika.foodcouriers.domain.usecase.user.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {


    @Provides
    @Singleton
    fun provideCategoriesUseCase(foodRepository: FoodRepository) =
        CategoriesFoodsUseCase(foodRepository)

    @Provides
    @Singleton
    fun provideFoodsUseCase(foodRepository: FoodRepository) =
        FoodsUseCase(foodRepository)

    @Provides
    @Singleton
    fun provideSearchFoodsUseCase(foodRepository: FoodRepository) =
        SearchFoodsUseCase(foodRepository)


    @Provides
    @Singleton
    fun provideActiveOrdersUseCase(orderRepository: OrderRepository) =
        ActiveOrdersUseCase(orderRepository)

    @Provides
    @Singleton
    fun provideDeleteOrdersByIdUseCase(orderRepository: OrderRepository) =
        DeleteOrdersByIdUseCase(orderRepository)

    @Provides
    @Singleton
    fun provideDeleteOrdersUseCase(orderRepository: OrderRepository) =
        DeleteOrdersUseCase(orderRepository)

    @Provides
    @Singleton
    fun provideOrdersCompleteUseCase(orderRepository: OrderRepository) =
        OrdersCompleteUseCase(orderRepository)

    @Provides
    @Singleton
    fun provideOrdersUseCase(orderRepository: OrderRepository) =
        OrdersUseCase(orderRepository)

    @Provides
    @Singleton
    fun provideUpdateOrdersUseCase(orderRepository: OrderRepository) =
        UpdateOrdersUseCase(orderRepository)


    @Provides
    @Singleton
    fun provideLocationUseCase(userRepository: UserRepository) =
        LocationUseCase(userRepository)


    @Provides
    @Singleton
    fun provideLoginUseCase(userRepository: UserRepository) =
        LoginUseCase(userRepository)

    @Provides
    @Singleton
    fun provideProfileUseCase(userRepository: UserRepository) =
        ProfileUseCase(userRepository)

    @Provides
    @Singleton
    fun provideRegisterUseCase(userRepository: UserRepository) =
        RegisterUseCase(userRepository)


}