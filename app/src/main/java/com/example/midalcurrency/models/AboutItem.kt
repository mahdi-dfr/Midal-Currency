package com.example.midalcurrency.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AboutItem(
    var web : String? = "no-data",
    var twt : String? = "no-data",
    var redit : String? = "no-data",
    var github : String? = "no-data",
    var moreInfo : String? = "no-data",
) : Parcelable
