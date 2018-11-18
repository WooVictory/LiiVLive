package app.woovictory.liiv_live.util.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.custom_check_dialog.*

/**
 * Created by VictoryWoo
 */
class CheckCompleteDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            check_dialog_x_btn -> dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        check_dialog_x_btn.setOnClickListener(this)


    }

    companion object {
        private val LAYOUT = R.layout.custom_check_dialog
    }
}