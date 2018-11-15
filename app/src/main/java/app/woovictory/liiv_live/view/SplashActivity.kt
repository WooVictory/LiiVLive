package app.woovictory.liiv_live.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.woovictory.liiv_live.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//
//
//        var gif_image = GlideDrawableImageViewTarget(splash_image)
//
//        Glide.with(this).load(R.drawable.splash).into(gif_image)
//
//        var handler = Handler()
//        handler.postDelayed({
//            startActivity<LoginActivity>()
//            finish()
//        },2000)
    }
}
