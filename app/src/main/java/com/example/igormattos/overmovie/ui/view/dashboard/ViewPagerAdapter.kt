package com.example.igormattos.overmovie.ui.view.dashboard

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.igormattos.overmovie.ui.view.dashboard.auth.LoginFragment
import com.example.igormattos.overmovie.ui.view.dashboard.auth.RegisterFragment

class ViewPagerAdapter(fragmentActivity: FragmentManager, lifecycle: Lifecycle):  FragmentStateAdapter(fragmentActivity, lifecycle) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { LoginFragment() }
            1 -> { RegisterFragment() }
            else -> {throw Resources.NotFoundException("Position Not Found")}
        }
    }
}