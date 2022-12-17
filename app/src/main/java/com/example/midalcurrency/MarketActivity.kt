package com.example.midalcurrency

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midalcurrency.adapters.MarketAdapter
import com.example.midalcurrency.api.ApiManager
import com.example.midalcurrency.data.NEXT_PAGE
import com.example.midalcurrency.databinding.ActivityMainBinding
import com.example.midalcurrency.models.AboutItem
import com.example.midalcurrency.models.CoinAboutData
import com.example.midalcurrency.models.TopCoinsModel
import com.google.gson.Gson

class MarketActivity : AppCompatActivity(), MarketAdapter.RecyclerCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var topCoinAdapter: MarketAdapter
    private lateinit var aboutDataMap : MutableMap<String, AboutItem>
    private var apiManager = ApiManager()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutRecycler.btnMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.livecoinwatch.com/"))
            startActivity(intent)
        }

        binding.swipeRefresh.setOnRefreshListener {
            initViews()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 1500)
        }

        convertJsonInfoToDataClass()

    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        getNewsFromApi()
        getTopCoinsFromApi()
    }

    private fun convertJsonInfoToDataClass() {
        val fileToString =
            applicationContext.assets
                .open("currencyinfo.json")
                .bufferedReader().use {
                    it.readText()
                }

        aboutDataMap = mutableMapOf()

        val gson = Gson()
        val aboutDataList = gson.fromJson(fileToString, CoinAboutData::class.java)

        aboutDataList.forEach {
            aboutDataMap[it.currencyName] = AboutItem(
                it.info.web,
                it.info.twt,
                it.info.reddit,
                it.info.github,
                it.info.desc,
            )
        }
    }

    private fun getNewsFromApi() {
        apiManager.getNewsFromApi(object : ApiManager.OnGetData<ArrayList<Pair<String, String>>> {
            override fun onSuccess(data: ArrayList<Pair<String, String>>) {
                refreshNews(data)
            }

            override fun onError(error: String) {
                Toast.makeText(this@MarketActivity, "Error: " + error, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getTopCoinsFromApi() {
        apiManager.getTopCoins(object : ApiManager.OnGetData<List<TopCoinsModel.Data>> {
            override fun onSuccess(data: List<TopCoinsModel.Data>) {
                setTopCoinList(data)
            }

            override fun onError(error: String) {
                Log.i("TAG", "onError: " + error)
                Toast.makeText(this@MarketActivity, "error => " + error, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun refreshNews(data: ArrayList<Pair<String, String>>) {
        var randomAccess = (1..49).random()
        binding.layoutNews.txtNews.text = data[randomAccess].first

        binding.layoutNews.txtNews.setOnClickListener {
            refreshNews(data)
        }

        binding.layoutNews.imgNews.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(data[randomAccess].second))
            startActivity(intent)
        }
    }

    fun setTopCoinList(data: List<TopCoinsModel.Data>) {
        topCoinAdapter = MarketAdapter(ArrayList(data), this)
        binding.layoutRecycler.recyclerMarket.adapter = topCoinAdapter
        binding.layoutRecycler.recyclerMarket.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onCoinItemClicked(dataCoin: TopCoinsModel.Data) {

        val goToNextPage = Intent(this@MarketActivity, CoinActivity::class.java)

        val bundle = Bundle()
        bundle.putParcelable("bundle1", aboutDataMap[dataCoin.coinInfo.name])
        bundle.putParcelable("bundle2", dataCoin)

        goToNextPage.putExtra(NEXT_PAGE, bundle)
        startActivity(goToNextPage)

    }


}