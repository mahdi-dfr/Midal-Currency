package com.example.midalcurrency.api

import com.example.midalcurrency.data.API_KEY
import com.example.midalcurrency.models.ChartData
import com.example.midalcurrency.models.NewsModel
import com.example.midalcurrency.models.TopCoinsModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    //@Headers(API_KEY)
    @GET("v2/news/")
    fun getNews(
        @Query("sortOrder") sortOrder: String = "popular"
    ) : Call<NewsModel>

   // @Headers(API_KEY)
    @GET("top/totalvolfull")
    fun getTopCoins(
        @Query("limit") limit: Int = 20,
        @Query("tsym") toSymbol: String = "USD",
    ) : Call<TopCoinsModel>

    @Headers(API_KEY)
    @GET("{period}")
    fun getChartData(
        @Path("period") period: String,
        @Query("fsym") fromSymbol : String,
        @Query("limit") limit : Int,
        @Query("aggregate") aggregate : Int,
        @Query("tsym") toSymbol : String = "USD"
    ) : Call<ChartData>
}