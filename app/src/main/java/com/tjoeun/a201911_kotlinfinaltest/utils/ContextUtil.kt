package com.tjoeun.a201911_kotlinfinaltest.utils

import android.content.Context

class ContextUtil {
    companion object {
        val prefName = "KotlinFinalTestPreference"

//        아이디 저장 여부 기록 항목
        val SAVE_ID_CHECKED = "SAVE_ID_CHECKED"

        fun setSaveIdChecked(context:Context, isChecked:Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            pref.edit().putBoolean(SAVE_ID_CHECKED, isChecked).apply()
        }

        fun getSaveIdChecked(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(SAVE_ID_CHECKED, false)
        }
    }
}