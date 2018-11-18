package app.woovictory.liiv_live.view.popup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.activity_survey_complete_pop_up.*

class SurveyCompletePopUpActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            survey_complete_x_btn-> finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_complete_pop_up)
        survey_complete_x_btn.setOnClickListener(this)
    }
}
