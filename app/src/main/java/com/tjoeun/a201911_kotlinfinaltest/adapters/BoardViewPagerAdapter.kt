package com.tjoeun.a201911_kotlinfinaltest.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tjoeun.a201911_kotlinfinaltest.fragments.BoardListFragment
import com.tjoeun.a201911_kotlinfinaltest.fragments.NoticeListFragment

class BoardViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                BoardListFragment()
            }
            else -> {
                NoticeListFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}