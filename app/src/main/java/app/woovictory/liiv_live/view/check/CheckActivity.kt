package app.woovictory.liiv_live.view.check

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.activity_check.*

class CheckActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            check_x_btn -> finish()
            check_okay_btn->{
                if(!flag){
                    flag = true
                    check_calendar_image.setImageResource(R.drawable.calendar_checked)
                    check_okay_btn.background.setColorFilter(ContextCompat.getColor(this, R.color.viewColor),PorterDuff.Mode.SRC_IN)
                    check_okay_btn.isClickable = false
                }
            }

        }
    }

    var flag : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        check_x_btn.setOnClickListener(this)
        check_okay_btn.setOnClickListener(this)
    }
}
