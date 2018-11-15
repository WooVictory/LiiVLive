package app.woovictory.liiv_live.view.survey

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.RadioButton
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.util.dialoog.SurveyCompleteDialog
import kotlinx.android.synthetic.main.fragment_survey.*
import kotlinx.android.synthetic.main.fragment_survey.view.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.windowManager

/**
 * Created by VictoryWoo
 */
class SurveyFragment : Fragment() {

    companion object {
        private const val CATEGORY = "category"

        fun newInstance(sectionCategory: String): SurveyFragment {
            val fragment = SurveyFragment()
            val args : Bundle? = Bundle()
            args?.putString(CATEGORY, sectionCategory)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_survey, container, false)

        setting(arguments?.getString(CATEGORY), view)
        println("woo 438 : ${arguments?.getString(CATEGORY)}")
        return view
    }

    var radioButtonClickListener = object : View.OnClickListener{
        override fun onClick(v: View?) {
            when(v!!){
                option1->{

                    /*FIXME
                    * dialog 객체를 생성
                    * dialog의 백그라운드 색상을 투명하게 만듦.
                    * dialog를 사용자에게 보여줌.
                    * */
                    var survey_complete_dialog = SurveyCompleteDialog(context!!)
                    survey_complete_dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    survey_complete_dialog.show()


                    /*FIXME
                    * 현재 기기의 화면을 구한다.
                    * */
                    var display = context!!.windowManager.defaultDisplay
                    var size = Point()
                    display.getSize(size)

                    var window : Window = survey_complete_dialog.window

                    var x = (size.x  * 0.8f).toInt()
                    var y = (size.y * 0.6f).toInt()
                    window.setGravity(Gravity.BOTTOM)
                    window.setLayout(x,y)





                    //window.setLayout(1100, WindowManager.LayoutParams.WRAP_CONTENT)
                }
            }
        }

    }

    fun setting(args : String?, view : View){

        view.option1.setOnClickListener(radioButtonClickListener)

        when(args){
            "1"->{
                view.parent_radio_group.removeView(view.option5)
                //view.option5.visibility = View.GONE
                //view.option1.text = "바뀐거니? 1"
                toast("11일?")
            }
            "2"->{

            }
            "3"->{

            }
            "4"->{

            }
            "5"->{

            }
            "6"->{
                view.option1.text = "6번의 1번 답지"
                view.option2.text = "6번의 1번 답지"
                view.option3.text = "6번의 1번 답지"
                view.option4.text = "6번의 1번 답지"
                view.option5.text = "6번의 1번 답지"
                if(view.option1.isChecked){
                    toast("ddd")
                }


            }
        }
    }
}