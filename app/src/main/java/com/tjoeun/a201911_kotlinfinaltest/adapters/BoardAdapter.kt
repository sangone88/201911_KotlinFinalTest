package com.tjoeun.a201911_kotlinfinaltest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tjoeun.a201911_kotlinfinaltest.R
import com.tjoeun.a201911_kotlinfinaltest.datas.BlackList


class BoardAdapter(context: Context, res:Int, list:ArrayList<BlackList>)
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



        return row
    }
}