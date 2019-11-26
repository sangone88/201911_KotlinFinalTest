package com.tjoeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable

class UserData : Serializable {
    var loginid = ""
    var name = ""
    var phone = ""

    companion object {
        fun getUserFromJsonObject(json:JSONObject) : UserData {
            val user = UserData()

            user.loginid = json.getString("login_id")
            user.name = json.getString("name")
            user.phone = json.getString("phone")

            return user
        }
    }
}