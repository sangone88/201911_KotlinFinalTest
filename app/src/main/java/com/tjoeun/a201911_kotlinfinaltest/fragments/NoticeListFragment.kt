package com.tjoeun.a201911_kotlinfinaltest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tjoeun.a201911_kotlinfinaltest.R
import com.tjoeun.a201911_kotlinfinaltest.adapters.NoticeAdapter
import com.tjoeun.a201911_kotlinfinaltest.datas.Notice
import com.tjoeun.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.fragment_notice_list.*
import org.json.JSONObject

class NoticeListFragment : BaseFragment() {

    var noticeList = ArrayList<Notice>()
    var noticeAdapter:NoticeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notice_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

    }

    override fun setValues() {
        noticeAdapter = NoticeAdapter(mContext!!, noticeList)
        noticeListView.adapter = noticeAdapter

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

                    noticeList.clear()
                    for (i in 0..notices.length()-1) {
                        noticeList.add(Notice.getNoticeFromJson(notices.getJSONObject(i)))
                    }
                    activity!!.runOnUiThread {
                        noticeAdapter?.notifyDataSetChanged()
                    }
                }
                else {
                    Toast.makeText(mContext!!, "서버 연결에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


}