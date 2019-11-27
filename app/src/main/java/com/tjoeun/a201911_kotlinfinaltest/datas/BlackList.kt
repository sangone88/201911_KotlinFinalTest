package com.tjoeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class BlackList :Serializable {

    var id = 0
    var phoneNum = ""
    var title = ""
    var content = ""
    var createdAt = Calendar.getInstance()
    var writer = UserData()

    var serverTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")


    companion object {
        fun getBlackListDataFromJson(json : JSONObject) : BlackList {
            var blackList = BlackList()

            blackList.id = json.getInt("id")
            blackList.phoneNum = json.getString("phone_num")
            blackList.title = json.getString("title")
            blackList.content = json.getString("content")
            blackList.createdAt.time = blackList.serverTimeFormat.parse(json.getString("created_at"))
            blackList.writer = UserData.getUserFromJsonObject(json.getJSONObject("writer"))


            return blackList
        }
    }
}