package app.woovictory.liiv_live.view.survey

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.SurveyFragmentAdapter
import kotlinx.android.synthetic.main.activity_survey.*

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)


        addFragment(SurveyFragment.newInstance("1"))


        surveyFragmentAdapter = SurveyFragmentAdapter(supportFragmentManager)
        /*FIXME
        * 처음에 통신을 먼저해서 질문 갯수를 받아와서 통신 함수 안에서 사이즈 받고
        * for문으로 생성해주고
        * */
        surveyFragmentAdapter.addItem(SurveyFragment.newInstance("2"))
        surveyFragmentAdapter.addItem(SurveyFragment())
        surveyFragmentAdapter.addItem(SurveyFragment())
        surveyFragmentAdapter.addItem(SurveyFragment())
        surveyFragmentAdapter.addItem(SurveyFragment())
        survey_viewpager.offscreenPageLimit = 6


        survey_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
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

        survey_viewpager.setCurrentItem(0)
        survey_viewpager.adapter = surveyFragmentAdapter
    }
}
