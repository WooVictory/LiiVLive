package app.woovictory.liiv_live.view.survey

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.SurveyFragmentAdapter
import app.woovictory.liiv_live.util.MeasureViewpager
import kotlinx.android.synthetic.main.activity_survey.*
import kotlinx.android.synthetic.main.content_home.*

class SurveyActivity : AppCompatActivity() {


    fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.survey_viewpager, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.survey_viewpager, fragment)
        transaction.commit()
    }

    lateinit var surveyFragmentAdapter: SurveyFragmentAdapter
    lateinit var custom_vp : MeasureViewpager
    var survey_num_items = listOf<String>("Q. 1","Q. 2","Q. 2-1","Q. 3","Q. 4","Q. 5","Q. 6")
    var survey_items = listOf<String>("현재 본인의 차를 보유하고 계신가요?","중고차를 판매해 본 경험이 있나요?"
        ,"어떤 채널을 통해 판매하셨나요?","차를 판매할 때 가장 어려운 점이 무엇인가요?","기존에 판매한 중고차 채널에 대한 만족도는?"
    ,"추후 중고차 판매 시 어떤 채널로 판매할 계획인가요?")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        custom_vp = survey_viewpager

        //addFragment(SurveyFragment.newInstance("1"))


        surveyFragmentAdapter = SurveyFragmentAdapter(supportFragmentManager)
        /*FIXME
        * 처음에 통신을 먼저해서 질문 갯수를 받아와서 통신 함수 안에서 사이즈 받고
        * for문으로 생성해주고
        * */
        surveyFragmentAdapter.addItem(SurveyDoubleFragment.newInstance("1"))
        surveyFragmentAdapter.addItem(SurveyDoubleFragment.newInstance("2"))
        //surveyFragmentAdapter.addItem(SurveyFragment.newInstance("1"))
        //surveyFragmentAdapter.addItem(SurveyFragment.newInstance("2"))
        surveyFragmentAdapter.addItem(SurveyFragment.newInstance("3"))
        surveyFragmentAdapter.addItem(SurveyFragment.newInstance("4"))
        surveyFragmentAdapter.addItem(SurveyFragment.newInstance("5"))
        surveyFragmentAdapter.addItem(SurveyFragment.newInstance("6"))
        custom_vp.offscreenPageLimit = 6
        survey_indicator.setItemMargin(10)
        survey_indicator.setAnimDuration(300)
        println("count는??"+surveyFragmentAdapter.count)
        survey_indicator.createDotPanel(surveyFragmentAdapter.count, R.drawable.dot_non, R.drawable.dot)


        custom_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                survey_indicator.selectDot(position)
                survey_question_number.text = survey_num_items[position]
                survey_question_title.text = survey_items[position]
                //resize(position)
                /*  when(position){
                      0->{
                          surveyFragmentAdapter.addItem(SurveyFragment.newInstance("1"))
                          //surveyFragmentAdapter.getItem(position)
                      }
                      1->{
                          surveyFragmentAdapter.addItem(SurveyFragment.newInstance("1"))
                      }
                      3->{
                          surveyFragmentAdapter.addItem(SurveyFragment.newInstance("1"))
                      }
                      4->{
                          surveyFragmentAdapter.addItem(SurveyFragment.newInstance("1"))
                      }
                      5->{
                          surveyFragmentAdapter.addItem(SurveyFragment.newInstance("1"))
                      }
                      6->{
                          surveyFragmentAdapter.addItem(SurveyFragment.newInstance("1"))
                      }
                  }*/
            }

        })

        custom_vp.setCurrentItem(0)
        custom_vp.adapter = surveyFragmentAdapter
    }

    fun resize(position : Int){
        var view : View = custom_vp.findViewWithTag(position)
        if(view == null) return

        view.measure(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)

        var width : Int = view.measuredWidth
        var height : Int = view.measuredHeight


        var params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(width,height)
        custom_vp.layoutParams = params


    }
}
