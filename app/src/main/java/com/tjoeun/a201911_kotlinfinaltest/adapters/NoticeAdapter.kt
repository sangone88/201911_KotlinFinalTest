package com.tjoeun.a201911_kotlinfinaltest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tjoeun.a201911_kotlinfinaltest.R
import com.tjoeun.a201911_kotlinfinaltest.datas.Notice


class NoticeAdapter(context: Context, res:Int, list:ArrayList<Notice>)
    : ArrayAdapter<Notice> (context, res, list) {

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list: ArrayList<Notice>) : this(context, R.layout.notice_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.notice_list_item, null)
        }

        var row = tempRow!!

        val data = mList.get(position)

        val titleTxt = row.findViewById<TextView>(R.id.titleTxt)
        val createAtTxt = row.findViewById<TextView>(R.id.createdAtTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        titleTxt.text = data.title
        contentTxt.text = data.content
        createAtTxt.text = data.getFormattedCreatedAt()

        return row
    }
}