package net.pubnative.lite.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.lang.Exception

class JsonUtils {
    companion object {
        val TAG = JsonUtils::class.java.simpleName

        @JvmStatic
        fun toFormattedJson(json: String): String {
            try {
                val jsonObject = JsonParser.parseString(json).asJsonObject

                val gson = GsonBuilder().setLenient().setPrettyPrinting().create()
                return gson.toJson(jsonObject)
            } catch (exception: Exception) {
                Logger.e(TAG, "Error parsing json: ", exception)
                return json
            }
        }
    }
}