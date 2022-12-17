package com.example.midalcurrency

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.midalcurrency.adapters.ChartAdapter
import com.example.midalcurrency.api.ApiManager
import com.example.midalcurrency.data.*
import com.example.midalcurrency.databinding.ActivityCoinBinding
import com.example.midalcurrency.models.AboutItem
import com.example.midalcurrency.models.ChartData
import com.example.midalcurrency.models.TopCoinsModel

class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding
    private lateinit var dataThisCoin: TopCoinsModel.Data
    private lateinit var mapData: AboutItem
    private var apiManager = ApiManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dataFromMarket = intent.getParcelableExtra<Bundle>(NEXT_PAGE)
        dataThisCoin = dataFromMarket!!.getParcelable("bundle2")!!

        if (dataFromMarket.getParcelable<AboutItem>("bundle1") != null) {
            mapData = dataFromMarket.getParcelable("bundle1")!!
        } else
            mapData = AboutItem()

        binding.layoutToolbarCoin.toolbar.title = dataThisCoin.coinInfo.fullName

        initUi()
    }

    private fun initUi() {
        initChartUi()
        initStatistics()
        initAbout()

    }

    @SuppressLint("SetTextI18n")
    private fun initChartUi() {

        var period: String = HOUR
        requestAndShowChart(period)
        binding.layoutChart.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_12h -> {
                    period = HOUR
                }
                R.id.radio_1d -> {
                    period = HOURS24
                }
                R.id.radio_1w -> {
                    period = WEEK
                }
                R.id.radio_1m -> {
                    period = MONTH
                }
                R.id.radio_3m -> {
                    period = MONTH3
                }
                R.id.radio_1y -> {
                    period = YEAR
                }
                R.id.radio_all -> {
                    period = ALL
                }
            }
            requestAndShowChart(period)
        }

        binding.layoutChart.txtChartPrice.text = dataThisCoin.dISPLAY.uSD.pRICE
        binding.layoutChart.txtChartChange1.text = " " + dataThisCoin.dISPLAY.uSD.cHANGE24HOUR

        if (dataThisCoin.coinInfo.fullName == "BUSD") {
            binding.layoutChart.txtChartChange2.text = "0%"
        } else {
            binding.layoutChart.txtChartChange2.text =
                if (dataThisCoin.rAW.uSD.cHANGEPCT24HOUR.toString().length >= 6) {
                    dataThisCoin.rAW.uSD.cHANGEPCT24HOUR.toString().substring(0, 5) + "%"
                } else
                    dataThisCoin.rAW.uSD.cHANGEPCT24HOUR.toString()
        }

        val taghir = dataThisCoin.rAW.uSD.cHANGEPCT24HOUR
        if (taghir > 0) {

            binding.layoutChart.txtChartChange2.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorGain
                )
            )

            binding.layoutChart.txtChartUpdown.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorGain
                )
            )

            binding.layoutChart.txtChartUpdown.text = "▲"

            binding.layoutChart.sparkviewMain.lineColor = ContextCompat.getColor(
                binding.root.context,
                R.color.colorGain
            )

        } else if (taghir < 0) {

            binding.layoutChart.txtChartChange2.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorLoss
                )
            )

            binding.layoutChart.txtChartUpdown.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.colorLoss
                )
            )

            binding.layoutChart.txtChartUpdown.text = "▼"

            binding.layoutChart.sparkviewMain.lineColor = ContextCompat.getColor(
                binding.root.context,
                R.color.colorLoss
            )


        }

        binding.layoutChart.sparkviewMain.setScrubListener {

            // show price kamel
            if (it == null) {
                binding.layoutChart.txtChartPrice.text = dataThisCoin.dISPLAY.uSD.pRICE
            } else {
                // show price this dot
                binding.layoutChart.txtChartPrice.text =
                    "$ " + (it as ChartData.Data).close.toString()
            }

        }

    }

    fun requestAndShowChart(period: String) {

        apiManager.getChartData(
            dataThisCoin.coinInfo.name,
            period,
            object : ApiManager.OnGetData<Pair<List<ChartData.Data>, ChartData.Data?>> {
                override fun onSuccess(data: Pair<List<ChartData.Data>, ChartData.Data?>) {
                    val chartAdapter = ChartAdapter(data.first, data.second?.open.toString())
                    binding.layoutChart.sparkviewMain.adapter = chartAdapter

                    Log.i("TAG", "testApi: " + data.first[1].high)
                }

                override fun onError(errorMessage: String) {
                    /*Toast.makeText(
                        this@CoinActivity,
                        "error => " + errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()*/

                    Log.i("TAG", "testApi: " + errorMessage)
                }
            })

    }

    private fun initStatistics() {
        binding.layoutStatistics.tvOpenAmount.text = dataThisCoin.dISPLAY.uSD.oPEN24HOUR
        binding.layoutStatistics.tvTodaysHighAmount.text = dataThisCoin.dISPLAY.uSD.hIGH24HOUR
        binding.layoutStatistics.tvTodayLowAmount.text = dataThisCoin.dISPLAY.uSD.lOW24HOUR
        binding.layoutStatistics.tvChangeTodayAmount.text = dataThisCoin.dISPLAY.uSD.cHANGE24HOUR
        binding.layoutStatistics.tvAlgorithm.text = dataThisCoin.coinInfo.algorithm
        binding.layoutStatistics.tvTotalVolume.text = dataThisCoin.dISPLAY.uSD.tOTALVOLUME24H
        binding.layoutStatistics.tvAvgMarketCapAmount.text = dataThisCoin.dISPLAY.uSD.mKTCAP
        binding.layoutStatistics.tvSupplyNumber.text = dataThisCoin.dISPLAY.uSD.sUPPLY

    }

    @SuppressLint("SetTextI18n")
    private fun initAbout() {
        binding.layoutAbout.txtAboutCoin.text = mapData.moreInfo
        binding.layoutAbout.txtWebsite.text = mapData.web
        binding.layoutAbout.txtGithub.text = mapData.github
        binding.layoutAbout.txtReddit.text = mapData.redit
        binding.layoutAbout.txtTwitter.text = mapData.twt
        binding.layoutAbout.txtTwitter.text = if (mapData.twt != "" && mapData.twt != "no-data") {
            "@" + mapData.twt
        } else
            "no-data"

        binding.layoutAbout.txtWebsite.setOnClickListener {
            if (mapData.web != "no-data" && mapData.redit != "") {
                openWebsiteDataCoin(mapData.web!!)
            }
        }
        binding.layoutAbout.txtGithub.setOnClickListener {
            if (mapData.github != "no-data" && mapData.redit != "") {
                openWebsiteDataCoin(mapData.github!!)
            }
        }

        binding.layoutAbout.txtTwitter.setOnClickListener {
            if (mapData.twt != "no-data" && mapData.redit != "") {
                openWebsiteDataCoin(BASE_URL_TWITTER + mapData.twt!!)
            }
        }

        binding.layoutAbout.txtReddit.setOnClickListener {
            if (mapData.redit != "no-data" && mapData.redit != "") {
                openWebsiteDataCoin(mapData.redit!!)
            }
        }


    }

    fun openWebsiteDataCoin(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}