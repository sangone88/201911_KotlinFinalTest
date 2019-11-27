package com.tjoeun.a201911_kotlinfinaltest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tjoeun.a201911_kotlinfinaltest.R
import com.tjoeun.a201911_kotlinfinaltest.datas.Notice
import com.tjoeun.a201911_kotlinfinaltest.utils.ServerUtil
import org.json.JSONObject

class NoticeListFragment : BaseFragment() {

    var noticeList = ArrayList<Notice>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notice_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

    }

    override fun setValues() {
        getNoticeFromServer()
    }

    fun getNoticeFromServer() {
        ServerUtil.getRequestNotice(mContext!!, object : ServerUtil.JasonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("공지목록응답", json.toString())

                val code = json.getInt("code")
                if (code == 200) {
                    val data = json.getJSONObject("data")
                    val notices = data.getJSONArray("notices")

                    for (i in 0..notices.length()-1) {
                        noticeList.add(Notice.getNoticeFromJson(notices.getJSONObject(i)))
                    }
                }
                else {
                    Toast.makeText(mContext!!, "서버 연결에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


}