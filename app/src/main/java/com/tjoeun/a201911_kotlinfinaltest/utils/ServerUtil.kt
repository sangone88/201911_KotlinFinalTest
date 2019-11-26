package com.tjoeun.a201911_kotlinfinaltest.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {
    interface JasonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {
        var BASE_URL = "http://192.168.0.26:5000"

        fun postRequestLogin(context: Context, userId:String, userPw:String, handler:JasonResponseHandler?) {

            var client = OkHttpClient()
            var url = "${BASE_URL}/auth"

//            POST/PUT/PATCH 메쏘드에서 요구하는 파라미터를 FormBody에 담아줌
            var formBody = FormBody.Builder()
                .add("login_id", userId)
                .add("password", userPw)
                .build()

//            실제로 날아갈 요청(request)을 생성.
            var request = Request.Builder()
                .url(url)
//                    post로 formBody를 가지고 감
                .post(formBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    실패
                    Log.e("서버통신에러", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
//                    성공
                    //                    Log.d("서버응답내용", body)
                    var body = response.body()!!.string()
//                    var json: JSONObject = JSONObject(body)  자료형 적을 필요도 없음
                    var json = JSONObject(body)
                    handler?.onResponse(json)

                }

            })
        }


    }

}