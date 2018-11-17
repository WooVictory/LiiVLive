package app.woovictory.liiv_live.view.popup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.home.HomeActivity
import kotlinx.android.synthetic.main.activity_live_exit_pop_up.*

class LiveExitPopUpActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            goToOngoingBtn -> {
                finish()
            }
            goToExitBtn -> {
                var intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                finish()
                startActivity(intent)

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_exit_pop_up)
        init()
    }

    fun init() {
        goToOngoingBtn.setOnClickListener(this)
        goToExitBtn.setOnClickListener(this)
    }
}
