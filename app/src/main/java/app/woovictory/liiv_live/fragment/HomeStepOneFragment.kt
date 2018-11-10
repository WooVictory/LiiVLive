package app.woovictory.liiv_live.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.liiv_live.R

/**
 * Created by VictoryWoo
 */
class HomeStepOneFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home_step_one, container, false)
        return view
    }
}