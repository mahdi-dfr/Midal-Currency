package com.example.midalcurrency.models


import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("Data")
    var `data`: List<Data>,
    @SerializedName("HasWarning")
    var hasWarning: Boolean,
    @SerializedName("Message")
    var message: String,
    @SerializedName("Promoted")
    var promoted: List<Any>,
    @SerializedName("RateLimit")
    var rateLimit: RateLimit,
    @SerializedName("Type")
    var type: Int
) {
    data class Data(
        @SerializedName("body")
        var body: String,
        @SerializedName("categories")
        var categories: String,
        @SerializedName("downvotes")
        var downvotes: String,
        @SerializedName("guid")
        var guid: String,
       /* @SerializedName("id")
        var id: String,*/
        @SerializedName("imageurl")
        var imageurl: String,
        @SerializedName("lang")
        var lang: String,
        @SerializedName("published_on")
        var publishedOn: Int,
        @SerializedName("source")
        var source: String,
        @SerializedName("source_info")
        var sourceInfo: SourceInfo,
        /*@SerializedName("tags")
        var tags: String,*/
        @SerializedName("title")
        var title: String,
        @SerializedName("upvotes")
        var upvotes: String,
        @SerializedName("url")
        var url: String
    ) {
        data class SourceInfo(
            @SerializedName("img")
            var img: String,
            @SerializedName("lang")
            var lang: String,
           /* @SerializedName("name")
            var name: String*/
        )
    }

    class RateLimit
}