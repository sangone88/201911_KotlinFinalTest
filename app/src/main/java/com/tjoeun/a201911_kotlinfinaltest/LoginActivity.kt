package com.tjoeun.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjoeun.a201911_kotlinfinaltest.datas.UserData
import com.tjoeun.a201911_kotlinfinaltest.utils.ContextUtil
import com.tjoeun.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        loginBtn.setOnClickListener {
            val userId = idEdt.text.toString()
            val userPw = pwEdt.text.toString()

            ServerUtil.postRequestLogin(mContext, userId, userPw, object : ServerUtil.JasonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    cho881020 / qlalfqjsgh! 로그인 성공 아이디 비번
                    Log.d("서버응답", json.toString())

                    val code = json.getInt("code")

                    if (code == 200) {
                        val data = json.getJSONObject("data")
                        val user = data.getJSONObject("user")
                        val token = data.getString("token")

//                        토큰값 폰에 저장
                        ContextUtil.setUserToken(mContext, token)

                        val userData = UserData.getUserFromJsonObject(user)

                        if (saveIdCheckBox.isChecked) {
                            ContextUtil.setUserId(mContext, userData.loginid)
                        }


                    }
                    else if (code == 400){
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
//                        500 => INTERNAL SERVER ERROR => 서버 내부 에러 => 서버개발자의 실수
//                        404 Not found => 없는 주소에 요청
//                        403 => 권한이 없는 요청
                        runOnUiThread {
                            Toast.makeText(mContext, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            })
        }


        saveIdCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                ContextUtil.setSaveIdChecked(mContext, isChecked)
        }

    }

    override fun setValues() {
        saveIdCheckBox.isChecked = ContextUtil.getSaveIdChecked(mContext)
        idEdt.setText(ContextUtil.getUserId(mContext))
    }
}
