package com.tjoeun.a201911_kotlinfinaltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lotto.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var lastBackButtonPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun onBackPressed() {
//        super.onBackPressed()

        val currentTime = System.currentTimeMillis()  // 현재시간을 간편하게 따고 싶을때 사용.

        if (currentTime - lastBackButtonPressedTime > 1500) {
//            백버튼을 오랜만에 누른 상황.
            Toast.makeText(mContext, "한번 더 뒤로 버튼을 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        else{
            finish()
        }
//        화면이 닫히지 않았다면 백버튼이 눌린 시간 기록
        lastBackButtonPressedTime = currentTime

    }

    override fun setupEvents() {
        lottoBtn.setOnClickListener {
            val intent = Intent(mContext, LottoActivity::class.java)
            startActivity(intent)
        }

    }

    override fun setValues() {

    }


}
