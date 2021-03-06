package com.mythio.weather.network

import com.mythio.weather.model.entity.Location
import com.mythio.weather.network.response.WeatherResponse
import com.mythio.weather.utils.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {

    @GET("forecast.json")
    suspend fun getWeatherAsync(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int
    ): Response<WeatherResponse>

    @GET("search.json")
    suspend fun getSearchLocationAsync(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): Response<List<Location>>
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}