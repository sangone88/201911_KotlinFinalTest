package com.tjoeun.a201911_kotlinfinaltest.utils

import android.content.Context

class ContextUtil {
    companion object {
        val prefName = "KotlinFinalTestPreference"

//        아이디 저장 여부 기록 항목
        val SAVE_ID_CHECKED = "SAVE_ID_CHECKED"
        val USER_TOKEN = "USER_TOKEN"

        fun setSaveIdChecked(context:Context, isChecked:Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            pref.edit().putBoolean(SAVE_ID_CHECKED, isChecked).apply()
        }

        fun getSaveIdChecked(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(SAVE_ID_CHECKED, false)
        }

        fun setUserToken(context:Context, token:String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            pref.edit().putString(USER_TOKEN, token).apply()
        }

        fun getUserToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!
        }
    }
}