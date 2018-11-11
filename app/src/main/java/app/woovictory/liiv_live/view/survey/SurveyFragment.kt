package app.woovictory.liiv_live.view.survey

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.liiv_live.R
import org.jetbrains.anko.support.v4.toast

/**
 * Created by VictoryWoo
 */
class SurveyFragment : Fragment() {

    companion object {
        private const val CATEGORY = "category"

        fun newInstance(sectionCategory: String): SurveyFragment {
            val fragment = SurveyFragment()
            val args = Bundle()
            args.putString(CATEGORY, sectionCategory)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_survey, container, false)

        setting(arguments!!.getString(CATEGORY)!!)
        return view
    }

    fun setting(args : String){
        when(args){
            "1"->{
                toast("11일?")
            }
            "2"->{

            }
        }
    }
}