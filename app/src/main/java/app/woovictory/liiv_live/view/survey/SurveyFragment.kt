package app.woovictory.liiv_live.view.survey

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.liiv_live.R

/**
 * Created by VictoryWoo
 */
class SurveyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_survey, container, false)
        return view
    }
}