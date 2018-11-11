package app.woovictory.liiv_live.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by VictoryWoo
 */
class SurveyFragmentAdapter(fm : FragmentManager)
    : FragmentStatePagerAdapter(fm) {
    var items : ArrayList<Fragment> = ArrayList()

    fun addItem(fragment: Fragment) {
        items.add(fragment)
    }
    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getCount(): Int = items.size
}