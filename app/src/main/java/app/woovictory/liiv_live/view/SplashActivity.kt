package app.woovictory.liiv_live.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import app.woovictory.liiv_live.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var handler = Handler()
        handler.postDelayed({

        },2000)
    }
}
