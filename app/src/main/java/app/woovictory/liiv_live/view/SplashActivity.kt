package app.woovictory.liiv_live.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.home.HomeActivity
import app.woovictory.liiv_live.view.login.LoginActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var gif_image = GlideDrawableImageViewTarget(splash_image)

        Glide.with(this).load(R.drawable.splash).into(gif_image)

        var handler = Handler()
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        },2000)
    }
}
