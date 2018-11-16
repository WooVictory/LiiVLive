package app.woovictory.liiv_live.util.dialoog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.custom_dialog_exchange_complete.*

/**
 * Created by VictoryWoo
 */
class ExchangeDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            exchange_complete_x_button -> dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        exchange_complete_x_button.setOnClickListener(this)
    }

    companion object {
        private val LAYOUT = R.layout.custom_dialog_exchange_complete
    }

}
