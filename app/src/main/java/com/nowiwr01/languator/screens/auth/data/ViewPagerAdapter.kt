package com.nowiwr01.languator.screens.auth.data

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    fm: FragmentManager,
    vararg fragments: Pair<String, Fragment>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentsList = listOf(*fragments)

    override fun getItem(position: Int) = fragmentsList[position].second

    override fun getCount() = fragmentsList.size

    override fun getPageTitle(position: Int) = fragmentsList[position].first
}