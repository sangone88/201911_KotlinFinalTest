package com.tjoeun.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        saveIdCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
//                체크박스가 체크 되는 상황
            }
            else{
//                해제되는 상황
            }
        }

    }

    override fun setValues() {

    }


}
