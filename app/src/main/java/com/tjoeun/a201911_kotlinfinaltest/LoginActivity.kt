package com.tjoeun.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeun.a201911_kotlinfinaltest.utils.ContextUtil
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
                ContextUtil.setSaveIdChecked(mContext, isChecked)
        }

    }

    override fun setValues() {
        saveIdCheckBox.isChecked = ContextUtil.getSaveIdChecked(mContext)
    }
}
