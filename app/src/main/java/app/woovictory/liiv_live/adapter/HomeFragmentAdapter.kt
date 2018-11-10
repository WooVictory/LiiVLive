package app.woovictory.liiv_live.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import app.woovictory.liiv_live.fragment.HomeStepOneFragment
import app.woovictory.liiv_live.fragment.HomeStepTwoFragment

/**
 * Created by VictoryWoo
 */
class HomeFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    var home_step_one_fragment = HomeStepOneFragment()
    var home_step_two_fragment = HomeStepTwoFragment()

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> return home_step_one_fragment
            1 -> return home_step_two_fragment
            else -> null
        }
    }

    override fun getCount(): Int = 2
}