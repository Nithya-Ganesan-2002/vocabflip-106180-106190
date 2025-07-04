package com.example.flashcardsfrontend

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// PUBLIC_INTERFACE
class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    /** Pager adapter for four main app tabs: Decks, Creation, Progress, Account/Auth. */
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        android.util.Log.d("MainPagerAdapter", "Creating fragment for tab position: $position")
        return when (position) {
            0 -> DecksFragment()
            1 -> CreateEditFragment()
            2 -> ProgressFragment()
            3 -> AuthFragment()
            else -> throw IndexOutOfBoundsException("Unknown tab position: $position")
        }
    }
}
