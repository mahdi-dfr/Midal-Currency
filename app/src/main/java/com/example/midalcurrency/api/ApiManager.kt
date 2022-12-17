package com.example.midalcurrency.api

import com.example.midalcurrency.data.*
import com.example.midalcurrency.models.ChartData
import com.example.midalcurrency.models.NewsModel
import com.example.midalcurrency.models.TopCoinsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    private var apiService : ApiService

   init {
       val retrofit =
           Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()

       apiService = retrofit.create(ApiService::class.java)
   }

    fun getNewsFromApi(onGetData: OnGetData<ArrayList<Pair<String, String>>>){

        apiService.getNews().enqueue(object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                var data = response.body()!!
                var dataToSend = ArrayList<Pair<String, String>>()
                data.data.forEach {
                    dataToSend.add(Pair(it.title, it.url))
                }

                onGetData.onSuccess(dataToSend)
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                onGetData.onError(t.message!!)
            }

        })

    }

    fun getTopCoins(onGetData: OnGetData<List<TopCoinsModel.Data>>){
        apiService.getTopCoins().enqueue(object : Callback<TopCoinsModel>{
            override fun onResponse(call: Call<TopCoinsModel>, response: Response<TopCoinsModel>) {

                val data = response.body()!!
                onGetData.onSuccess(data.data)

            }

            override fun onFailure(call: Call<TopCoinsModel>, t: Throwable) {
                onGetData.onError(t.message!!)
            }

        })
    }

    /*fun getChartData(period: String, symbol: String , onGetData: OnGetData<Pair<List<ChartData.Data>, ChartData.Data?>>){

        var histoPeriod = ""
        var limit = 30
        var aggregate = 1

        when(period){
            HOUR ->{
                histoPeriod = HISTO_MINUTE
                limit = 60
                aggregate = 12
            }

            HOURS24->{
                histoPeriod = HISTO_DAY
                limit = 24
            }

            WEEK ->{
                histoPeriod = HISTO_HOUR
                aggregate = 6
            }

            MONTH->{
                histoPeriod = HISTO_DAY
                limit = 30
            }

            MONTH3->{
                histoPeriod = HISTO_DAY
                limit = 90
            }

            YEAR ->{
                histoPeriod = HISTO_DAY
                aggregate = 13
            }

            ALL ->{
                histoPeriod = HISTO_DAY
                aggregate = 30
                limit = 2000
            }
        }

        apiService.getChart(histoPeriod, symbol, limit, aggregate).enqueue(object : Callback<ChartData>{
            override fun onResponse(call: Call<ChartData>, response: Response<ChartData>) {
                var fullData = response.body()!!
                var data1= fullData.data
                var data2 = fullData.data.maxByOrNull { it.close.toFloat() }
                val returningData = Pair(data1, data2)

                onGetData.onSuccess(returningData)
            }

            override fun onFailure(call: Call<ChartData>, t: Throwable) {
                onGetData.onError(t.message!!)
            }

        })

    }*/


    fun getChartData(
        symbol: String,
        period: String,
        apiCallback: OnGetData<Pair<List<ChartData.Data>, ChartData.Data?>>
    ) {

        var histoPeriod = ""
        var limit = 30
        var aggregate = 1

        when (period) {

            HOUR -> {
                histoPeriod = HISTO_MINUTE
                limit = 60
                aggregate = 12
            }

            HOURS24 -> {
                histoPeriod = HISTO_HOUR
                limit = 24
            }

            MONTH -> {
                histoPeriod = HISTO_DAY
                limit = 30
            }

            MONTH3 -> {
                histoPeriod = HISTO_DAY
                limit = 90
            }

            WEEK -> {
                histoPeriod = HISTO_HOUR
                aggregate = 6
            }

            YEAR -> {
                histoPeriod = HISTO_DAY
                aggregate = 13
            }

            ALL -> {
                histoPeriod = HISTO_DAY
                aggregate = 30
                limit = 2000
            }

        }

        apiService.getChartData(histoPeriod, symbol, limit, aggregate)
            .enqueue(object : Callback<ChartData> {
                override fun onResponse(call: Call<ChartData>, response: Response<ChartData>) {

                    val dataFull = response.body()!!
                    val data1 = dataFull.data
                    val data2 = dataFull.data.maxByOrNull { it.close.toFloat() }
                    val returningData = Pair(data1, data2)

                    apiCallback.onSuccess(returningData)

                }

                override fun onFailure(call: Call<ChartData>, t: Throwable) {
                    apiCallback.onError(t.message!!)
                }

            })

    }


    interface OnGetData<T>{
        fun onSuccess(data: T)

        fun onError(error: String)

    }

}