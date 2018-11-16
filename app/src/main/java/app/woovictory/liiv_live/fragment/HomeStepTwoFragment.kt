package app.woovictory.liiv_live.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.pointItemDataList
import kotlinx.android.synthetic.main.fragment_home_step_two.view.*

/**
 * Created by VictoryWoo
 */
class HomeStepTwoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home_step_two, container, false)
        view.home_step_two_level_text.text = "LV "+pointItemDataList.level
        return view
    }
}