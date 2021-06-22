package am.mtgtrade.app.ui.models

import android.net.Uri

data class Offer(
    val id:String? = "noId",
    val userName:String? = "noName",
    val userEmail:String? = "noName@no.no",
    val userPhone:String? = "000-000-000",
    val cardName:String? = "NoCardName",
    val date:String? = "1970.01.01",
    val imgUri:String? = "/"
)
