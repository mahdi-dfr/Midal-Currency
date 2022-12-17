package com.example.midalcurrency.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midalcurrency.data.BASE_URL_IMAGE
import com.example.midalcurrency.databinding.ModuleRecyclerItemBinding
import com.example.midalcurrency.models.TopCoinsModel

class TopCoinAdapter(var itemList: List<TopCoinsModel.Data>) :
    RecyclerView.Adapter<TopCoinAdapter.TopCoinViewHolder>() {

    private lateinit var binding: ModuleRecyclerItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCoinViewHolder {
        binding =
            ModuleRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopCoinViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TopCoinViewHolder, position: Int) {
        holder.topCoinBind(position)
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class TopCoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun topCoinBind(position: Int) {

            var item = itemList[position]
            binding.txtCoinName.text = item.coinInfo.fullName
            binding.txtPrice.text = item.dISPLAY.uSD.pRICE

            binding.txtTaghir.text = "1258"     //"% " + item.rAW.uSD.cHANGE24HOUR.toString().substring(0, 4)

            /*var marketCap = item.rAW.uSD.mKTCAP / 1000000000
            var marketIndex = marketCap.toString().indexOf(".")*/

            binding.txtMarketCap.text = "5464"     //"$ "+ marketCap.toString().substring(0, marketIndex+3)+ " B"


                Glide
                    .with(itemView)
                    .load(BASE_URL_IMAGE + item.coinInfo.imageUrl)
                    .into(binding.imgItem)


        }

    }
}