package app.woovictory.liiv_live.util.dialoog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.custom_dialog_survey.*
import org.jetbrains.anko.startActivity

/**
 * Created by VictoryWoo
 */
class SurveyDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            goToSurveyBtn -> {
                dismiss()
                context.startActivity<SurveyActivity>()

            }
            survey_dialog_x_button -> dismiss()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        goToSurveyBtn.setOnClickListener(this)
        survey_dialog_x_button.setOnClickListener(this)

    }

    companion object {
        private val LAYOUT = R.layout.custom_dialog_survey
    }
}