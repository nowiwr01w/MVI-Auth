package com.nowiwr01.languator.screens.auth

import com.nowiwr01.languator.R
import com.nowiwr01.languator.base.BaseFragment
import com.nowiwr01.languator.databinding.FragmentLoginBinding
import com.nowiwr01.languator.screens.auth.login.SignInFragment
import com.nowiwr01.languator.screens.auth.signup.SignUpFragment
import com.nowiwr01.languator.screens.auth.data.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment<FragmentLoginBinding>() {

    override val layoutResId = R.layout.fragment_login

    override fun setViews() {
        hideBottomBar()
        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(
            childFragmentManager, "Sign In" to SignInFragment(), "Sign Up" to SignUpFragment()
        )
        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }
}