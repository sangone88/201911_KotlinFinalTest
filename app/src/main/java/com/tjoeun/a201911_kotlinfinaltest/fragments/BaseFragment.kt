package com.tjoeun.a201911_kotlinfinaltest.fragments

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    var mContext = activity

    abstract fun setupEvents()
    abstract fun setValues()
}