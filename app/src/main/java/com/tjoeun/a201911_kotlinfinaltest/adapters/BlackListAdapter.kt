package com.tjoeun.a201911_kotlinfinaltest.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tjoeun.a201911_kotlinfinaltest.R
import com.tjoeun.a201911_kotlinfinaltest.datas.BlackList


class BlackListAdapter(context: Context, res:Int, list:ArrayList<BlackList>)
    : ArrayAdapter<BlackList> (context, res, list) {

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list: ArrayList<BlackList>) : this(context, R.layout.board_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.board_list_item, null)
        }

        var row = tempRow!!

        val data = mList.get(position)

        val categoryTxt = row.findViewById<TextView>(R.id.categoryTxt)
        val writerNameTxt = row.findViewById<TextView>(R.id.writerNameTxt)
        val titleTxt = row.findViewById<TextView>(R.id.writerNameTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)


        categoryTxt.text = data.writer.category.title.first().toString()
//        categoryTxt.text = data.writer.category.title.substring(0,1)
//        XML로 만든 백그라운드의 컬러를 바꿔주는 코드
        categoryTxt.background.setColorFilter(Color.parseColor(data.writer.category.color), PorterDuff.Mode.SRC_IN)
        writerNameTxt.text = data.writer.name
        titleTxt.text = data.title
        contentTxt.text = data.content


        return row
    }
}