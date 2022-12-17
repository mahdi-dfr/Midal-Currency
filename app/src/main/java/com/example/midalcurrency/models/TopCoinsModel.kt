package com.example.midalcurrency.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TopCoinsModel(
    @SerializedName("Data")
    var `data`: List<Data>,
    @SerializedName("HasWarning")
    var hasWarning: Boolean,
    @SerializedName("Message")
    var message: String,
    @SerializedName("MetaData")
    var metaData: MetaData,
    @SerializedName("RateLimit")
    var rateLimit: RateLimit,
    @SerializedName("SponsoredData")
    var sponsoredData: List<Any>,
    @SerializedName("Type")
    var type: Int
) {
    @Parcelize
    data class Data(
        @SerializedName("CoinInfo")
        var coinInfo: CoinInfo,
        @SerializedName("DISPLAY")
        var dISPLAY: DISPLAY,
        @SerializedName("RAW")
        var rAW: RAW
    ):Parcelable  {

        @Parcelize
        data class CoinInfo(
            @SerializedName("Algorithm")
            var algorithm: String,
            @SerializedName("AssetLaunchDate")
            var assetLaunchDate: String,
            /*@SerializedName("BlockNumber")
            var blockNumber: Int,
            @SerializedName("BlockReward")
            var blockReward: Double,
            @SerializedName("BlockTime")
            var blockTime: Double,*/
            @SerializedName("DocumentType")
            var documentType: String,
            @SerializedName("FullName")
            var fullName: String,
            @SerializedName("Id")
            var id: String,
            @SerializedName("ImageUrl")
            var imageUrl: String,
            @SerializedName("Internal")
            var `internal`: String,
           /* @SerializedName("MaxSupply")
            var maxSupply: Double,*/
            @SerializedName("Name")
            var name: String,
            /*@SerializedName("NetHashesPerSecond")
            var netHashesPerSecond: Long,*/
            @SerializedName("ProofType")
            var proofType: String,
            @SerializedName("Rating")
            var rating: Rating,
            @SerializedName("Type")
            var type: Int,
            @SerializedName("Url")
            var url: String
        ) : Parcelable{

            @Parcelize
            data class Rating(
                @SerializedName("Weiss")
                var weiss: Weiss
            ) : Parcelable{

                @Parcelize
                data class Weiss(
                    @SerializedName("MarketPerformanceRating")
                    var marketPerformanceRating: String,
                    @SerializedName("Rating")
                    var rating: String,
                    @SerializedName("TechnologyAdoptionRating")
                    var technologyAdoptionRating: String
                ) : Parcelable
            }
        }

        @Parcelize
        data class DISPLAY(
            @SerializedName("USD")
            var uSD: USD
        ) :Parcelable {

            @Parcelize
            data class USD(
                @SerializedName("CHANGE24HOUR")
                var cHANGE24HOUR: String,
                @SerializedName("CHANGEDAY")
                var cHANGEDAY: String,
                @SerializedName("CHANGEHOUR")
                var cHANGEHOUR: String,
                @SerializedName("CHANGEPCT24HOUR")
                var cHANGEPCT24HOUR: String,
                @SerializedName("CHANGEPCTDAY")
                var cHANGEPCTDAY: String,
                @SerializedName("CHANGEPCTHOUR")
                var cHANGEPCTHOUR: String,
                @SerializedName("CIRCULATINGSUPPLY")
                var cIRCULATINGSUPPLY: String,
                @SerializedName("CIRCULATINGSUPPLYMKTCAP")
                var cIRCULATINGSUPPLYMKTCAP: String,
                @SerializedName("CONVERSIONSYMBOL")
                var cONVERSIONSYMBOL: String,
                @SerializedName("CONVERSIONTYPE")
                var cONVERSIONTYPE: String,
                @SerializedName("FROMSYMBOL")
                var fROMSYMBOL: String,
                @SerializedName("HIGH24HOUR")
                var hIGH24HOUR: String,
                @SerializedName("HIGHDAY")
                var hIGHDAY: String,
                @SerializedName("HIGHHOUR")
                var hIGHHOUR: String,
                @SerializedName("IMAGEURL")
                var iMAGEURL: String,
                @SerializedName("LASTMARKET")
                var lASTMARKET: String,
                @SerializedName("LASTTRADEID")
                var lASTTRADEID: String,
                @SerializedName("LASTUPDATE")
                var lASTUPDATE: String,
                @SerializedName("LASTVOLUME")
                var lASTVOLUME: String,
                @SerializedName("LASTVOLUMETO")
                var lASTVOLUMETO: String,
                @SerializedName("LOW24HOUR")
                var lOW24HOUR: String,
                @SerializedName("LOWDAY")
                var lOWDAY: String,
                @SerializedName("LOWHOUR")
                var lOWHOUR: String,
                @SerializedName("MARKET")
                var mARKET: String,
                @SerializedName("MKTCAP")
                var mKTCAP: String,
                @SerializedName("MKTCAPPENALTY")
                var mKTCAPPENALTY: String,
                @SerializedName("OPEN24HOUR")
                var oPEN24HOUR: String,
                @SerializedName("OPENDAY")
                var oPENDAY: String,
                @SerializedName("OPENHOUR")
                var oPENHOUR: String,
                @SerializedName("PRICE")
                var pRICE: String,
                @SerializedName("SUPPLY")
                var sUPPLY: String,
                @SerializedName("TOPTIERVOLUME24HOUR")
                var tOPTIERVOLUME24HOUR: String,
                @SerializedName("TOPTIERVOLUME24HOURTO")
                var tOPTIERVOLUME24HOURTO: String,
                @SerializedName("TOSYMBOL")
                var tOSYMBOL: String,
                @SerializedName("TOTALTOPTIERVOLUME24H")
                var tOTALTOPTIERVOLUME24H: String,
                @SerializedName("TOTALTOPTIERVOLUME24HTO")
                var tOTALTOPTIERVOLUME24HTO: String,
                @SerializedName("TOTALVOLUME24H")
                var tOTALVOLUME24H: String,
                @SerializedName("TOTALVOLUME24HTO")
                var tOTALVOLUME24HTO: String,
                @SerializedName("VOLUME24HOUR")
                var vOLUME24HOUR: String,
                @SerializedName("VOLUME24HOURTO")
                var vOLUME24HOURTO: String,
                @SerializedName("VOLUMEDAY")
                var vOLUMEDAY: String,
                @SerializedName("VOLUMEDAYTO")
                var vOLUMEDAYTO: String,
                @SerializedName("VOLUMEHOUR")
                var vOLUMEHOUR: String,
                @SerializedName("VOLUMEHOURTO")
                var vOLUMEHOURTO: String
            ) : Parcelable
        }

        @Parcelize
        data class RAW(
            @SerializedName("USD")
            var uSD: USD
        ) : Parcelable{

            @Parcelize
            data class USD(
                @SerializedName("CHANGE24HOUR")
                var cHANGE24HOUR: Double,
                @SerializedName("CHANGEDAY")
                var cHANGEDAY: Double,
                @SerializedName("CHANGEHOUR")
                var cHANGEHOUR: Double,
                @SerializedName("CHANGEPCT24HOUR")
                var cHANGEPCT24HOUR: Double,
                @SerializedName("CHANGEPCTDAY")
                var cHANGEPCTDAY: Double,
                @SerializedName("CHANGEPCTHOUR")
                var cHANGEPCTHOUR: Double,
                @SerializedName("CIRCULATINGSUPPLY")
                var cIRCULATINGSUPPLY: Double,
                @SerializedName("CIRCULATINGSUPPLYMKTCAP")
                var cIRCULATINGSUPPLYMKTCAP: Double,
                @SerializedName("CONVERSIONSYMBOL")
                var cONVERSIONSYMBOL: String,
                @SerializedName("CONVERSIONTYPE")
                var cONVERSIONTYPE: String,
                @SerializedName("FLAGS")
                var fLAGS: String,
                @SerializedName("FROMSYMBOL")
                var fROMSYMBOL: String,
                @SerializedName("HIGH24HOUR")
                var hIGH24HOUR: Double,
                @SerializedName("HIGHDAY")
                var hIGHDAY: Double,
                @SerializedName("HIGHHOUR")
                var hIGHHOUR: Double,
                @SerializedName("IMAGEURL")
                var iMAGEURL: String,
                @SerializedName("LASTMARKET")
                var lASTMARKET: String,
                @SerializedName("LASTTRADEID")
                var lASTTRADEID: String,
                @SerializedName("LASTUPDATE")
                var lASTUPDATE: Int,
                @SerializedName("LASTVOLUME")
                var lASTVOLUME: Double,
                @SerializedName("LASTVOLUMETO")
                var lASTVOLUMETO: Double,
                @SerializedName("LOW24HOUR")
                var lOW24HOUR: Double,
                @SerializedName("LOWDAY")
                var lOWDAY: Double,
                @SerializedName("LOWHOUR")
                var lOWHOUR: Double,
                @SerializedName("MARKET")
                var mARKET: String,
                @SerializedName("MEDIAN")
                var mEDIAN: Double,
                @SerializedName("MKTCAP")
                var mKTCAP: Double,
                @SerializedName("MKTCAPPENALTY")
                var mKTCAPPENALTY: Int,
                @SerializedName("OPEN24HOUR")
                var oPEN24HOUR: Double,
                @SerializedName("OPENDAY")
                var oPENDAY: Double,
                @SerializedName("OPENHOUR")
                var oPENHOUR: Double,
                @SerializedName("PRICE")
                var pRICE: Double,
                @SerializedName("SUPPLY")
                var sUPPLY: Double,
                @SerializedName("TOPTIERVOLUME24HOUR")
                var tOPTIERVOLUME24HOUR: Double,
                @SerializedName("TOPTIERVOLUME24HOURTO")
                var tOPTIERVOLUME24HOURTO: Double,
                @SerializedName("TOSYMBOL")
                var tOSYMBOL: String,
                @SerializedName("TOTALTOPTIERVOLUME24H")
                var tOTALTOPTIERVOLUME24H: Double,
                @SerializedName("TOTALTOPTIERVOLUME24HTO")
                var tOTALTOPTIERVOLUME24HTO: Double,
                @SerializedName("TOTALVOLUME24H")
                var tOTALVOLUME24H: Double,
                @SerializedName("TOTALVOLUME24HTO")
                var tOTALVOLUME24HTO: Double,
                @SerializedName("TYPE")
                var tYPE: String,
                @SerializedName("VOLUME24HOUR")
                var vOLUME24HOUR: Double,
                @SerializedName("VOLUME24HOURTO")
                var vOLUME24HOURTO: Double,
                @SerializedName("VOLUMEDAY")
                var vOLUMEDAY: Double,
                @SerializedName("VOLUMEDAYTO")
                var vOLUMEDAYTO: Double,
                @SerializedName("VOLUMEHOUR")
                var vOLUMEHOUR: Double,
                @SerializedName("VOLUMEHOURTO")
                var vOLUMEHOURTO: Double
            ) : Parcelable
        }
    }

    data class MetaData(
        @SerializedName("Count")
        var count: Int
    )

    class RateLimit
}