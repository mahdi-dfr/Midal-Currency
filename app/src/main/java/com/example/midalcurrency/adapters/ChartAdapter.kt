package com.example.midalcurrency.adapters

import com.example.midalcurrency.models.ChartData
import com.robinhood.spark.SparkAdapter

class ChartAdapter(
    var list: List<ChartData.Data>,
    baseLine: String) : SparkAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(index: Int): Any {
        return list[index]
    }

    override fun getY(index: Int): Float {
        return list[index].close.toFloat()
    }

    /*override fun hasBaseLine(): Boolean {
        return true
    }
    override fun getBaseLine(): Float {
        return baseLine?.toFloat()  ?: super.getBaseLine()
    }
*/
}