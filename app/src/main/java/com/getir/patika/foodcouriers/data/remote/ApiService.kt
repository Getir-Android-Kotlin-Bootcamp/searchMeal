package com.getir.patika.foodcouriers.data.remote

import com.getir.patika.foodcouriers.domain.model.Food
import com.getir.patika.foodcouriers.domain.model.Location
import com.getir.patika.foodcouriers.domain.model.Login
import com.getir.patika.foodcouriers.domain.model.Orders
import com.getir.patika.foodcouriers.domain.model.Profile
import com.getir.patika.foodcouriers.domain.model.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("register")
    fun setRegister(@Body register: Register): Call<String>

    @POST("login")
    fun setLogin(@Body login: Login): Call<String>

    @GET("foods")
    fun getAllFoods(): Call<List<Food>>

    @GET("profile/{userId}")
    fun getProfile(@Path("userId") userId: String): Call<Profile>

    @POST("profile/{userId}/location")
    fun setLocation(@Path("userId") userId: String,@Body location: Location): Call<Boolean>

    @GET("foods")
    fun getSearchFoods(@Query("search") search: String): Call<List<Food>>

    @GET("foods/categories")
    fun getCategoriesFoods(@Query("type") type: String): Call<List<Food>>

    @POST("orders/{userId}/items")
    fun setOrders(@Path("userId") userId: String,@Body foodId: Int): Call<Any>

    @GET("orders/{userId}")
    fun getActiveOrders(@Path("userId") userId: String): Call<Orders>

    @POST("orders/items/{itemId}")
    fun setUpdateOrder(@Path("itemId") itemId: Int,@Body quantity: Int): Call<Any>

    @DELETE("orders/{userId}/current")
    fun deleteCurrentOrders(@Path("userId") userId: String): Call<Any>

    @DELETE("orders/{userId}/{orderId}")
    fun deleteOrderWithById(@Path("userId") userId: String,@Path("orderId") orderId: Int):  Call<Any>

    @GET("orders/{userId}/complete")
    fun getOrderComplete(@Path("userId") userId: String): Call<Any>




}