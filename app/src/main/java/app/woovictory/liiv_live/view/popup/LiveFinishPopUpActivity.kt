package app.woovictory.liiv_live.view.popup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import com.bumptech.glide.Glide
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
        Glide.with(this@LiveFinishPopUpActivity).load(SharedPreferenceController.getMyImage(applicationContext)).into(live_finish_image)
        live_finish_usr_tv.text = SharedPreferenceController.getMyNick(applicationContext)

    }
}
