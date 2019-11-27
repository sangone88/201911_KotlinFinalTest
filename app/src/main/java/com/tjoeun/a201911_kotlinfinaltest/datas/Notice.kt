package com.tjoeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Notice :Serializable {

    var id = 0
    var title = ""
    var content = ""
    var createdAt = Calendar.getInstance()

    var serverTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    companion object {
        fun getNoticeFromJson(json : JSONObject) : Notice {
            var notice = Notice()

            notice.id = json.getInt("id")
            notice.title = json.getString("title")
            notice.content = json.getString("content")

            notice.createdAt.time = notice.serverTimeFormat.parse(json.getString("created_at"))

            return notice
        }
    }
}