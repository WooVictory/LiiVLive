package app.woovictory.liiv_live.view.popup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.activity_sospopup.*
import org.jetbrains.anko.startActivity


class SOSPopupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sospopup)

        setOnClickListener()

    }

    private fun setOnClickListener(){

        // 설문하기 버튼
        goToSurveyBtn.setOnClickListener {
            startActivity<SurveyActivity>()
            finish()
        }

        // X 버튼
        survey_dialog_x_button.setOnClickListener {
            finish()
        }
    }
}
