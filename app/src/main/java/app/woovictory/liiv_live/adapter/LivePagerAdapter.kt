package app.woovictory.liiv_live.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import app.woovictory.liiv_live.fragment.ChattingFragment
import app.woovictory.liiv_live.fragment.HomeStepOneFragment
import app.woovictory.liiv_live.fragment.HomeStepTwoFragment
import app.woovictory.liiv_live.fragment.LiveFakeFragment

/**
 * Created by VictoryWoo
 */
class LivePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    var fake_fragment = LiveFakeFragment()
    var chatting_fragment = ChattingFragment()
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> return fake_fragment
            1 -> return chatting_fragment
            else -> null
        }

    }

    override fun getCount(): Int = 2
}