package com.tjoeun.a201911_kotlinfinaltest.datas

import org.json.JSONObject
import java.io.Serializable

class Category :Serializable {

    var id = 0
    var title = ""
    var color = ""

    companion object {
        fun getCategoryFromJson(json: JSONObject) : Category {
            val category = Category()

            category.id = json.getInt("id")
            category.title = json.getString("title")
            category.color = json.getString("color")

            return category
        }
    }
}