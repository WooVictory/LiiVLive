package app.woovictory.liiv_live.view.popup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.activity_live_finish_pop_up.*

class LiveFinishPopUpActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            goToOkayBtn->{
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_finish_pop_up)
        goToOkayBtn.setOnClickListener(this)

    }
}
