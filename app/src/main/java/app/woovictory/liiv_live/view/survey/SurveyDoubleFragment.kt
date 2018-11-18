package app.woovictory.liiv_live.view.survey

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.fragment_survey.view.*
import kotlinx.android.synthetic.main.fragment_survey_double.view.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by VictoryWoo
 */
class SurveyDoubleFragment: Fragment() {

    companion object {
        private const val CATEGORY = "category"

        fun newInstance(sectionCategory: String): SurveyDoubleFragment{
            val fragment = SurveyDoubleFragment()
            val args : Bundle? = Bundle()
            args?.putString(CATEGORY, sectionCategory)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_survey_double, container, false)

        setting(arguments?.getString(CATEGORY), view)
        println("woo 438 : ${arguments?.getString(CATEGORY)}")
        return view
    }

    fun setting(args : String?, view : View){
        when(args){
            "1"->{
                //view.parent_radio_group.removeView(view.option5)
                view.double_option1.text = "예"
                view.double_option2.text = "아니오"
            }
            "2"->{
                view.double_option1.text = "예"
                view.double_option2.text = "아니오"
            }
        }
    }
}