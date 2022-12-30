package com.justagg.app.util

import com.justagg.app.data.response.DummyResponse

object Constant {

    var k_SERVER_IP = "https://assetmanager-mulleadys.azurewebsites.net/MOBILE_APP_API/y35g1f2/"
    var k_PIN = "123"

    internal const val k_checkLogin = "checkLogin"
    internal const val k_dummyData = "dummyData"
    internal const val k_username = "username"
    internal const val k_baseUrl = "baseUrl"
    internal const val k_authId = "userAuthId"
    internal const val k_selectedLanguage = "selectedLanguage"

    internal const val k_Success = "Success"
    internal const val k_Error = "Error"

    val mListDummyLinks = mutableListOf(
        DummyResponse("Instagram"),
        DummyResponse("Facebook"),
        DummyResponse("LinkedIn"),
        DummyResponse("Snapchat"),
        DummyResponse("Twitter"),
    )

}