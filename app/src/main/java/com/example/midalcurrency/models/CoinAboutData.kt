package com.example.midalcurrency.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

class CoinAboutData : ArrayList<CoinAboutData.CoinAboutDataItem>(){
    @Parcelize
    data class CoinAboutDataItem(
        @SerializedName("currencyName")
        var currencyName: String,
        @SerializedName("info")
        var info: Info
    ) : Parcelable {
        @Parcelize
        data class Info(
            @SerializedName("desc")
            var desc: String,
            @SerializedName("forum")
            var forum: String,
            @SerializedName("github")
            var github: String,
            @SerializedName("reddit")
            var reddit: String,
            @SerializedName("twt")
            var twt: String,
            @SerializedName("web")
            var web: String
        ) : Parcelable
    }
}