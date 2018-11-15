package app.woovictory.liiv_live.view.live

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.LivePagerAdapter
import app.woovictory.liiv_live.util.youtube.CustomPlayerUIController
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.activity_live.*
import kotlinx.android.synthetic.main.content_home.*

class LiveActivity : AppCompatActivity() {

    private val videoIds = arrayOf("98-1s3ls26c", "98-1s3ls26c")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)


        live_vp.adapter = LivePagerAdapter(supportFragmentManager)
        live_vp.setCurrentItem(0)

        live_indicator.setItemMargin(10)
        live_indicator.setAnimDuration(300)
        live_indicator.createDotPanel(2, R.drawable.dot_non, R.drawable.dot)

        live_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {
                live_indicator.selectDot(position)


            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

        })

        lifecycle.addObserver(live_youtube_plyer_view)

        val customPlayerUI = live_youtube_plyer_view.inflateCustomPlayerUI(R.layout.custom_play_button)

        live_youtube_plyer_view.initialize({ youTubePlayer ->

            val customPlayerUIController =
                CustomPlayerUIController(
                    this,
                    customPlayerUI,
                    youTubePlayer,
                    live_youtube_plyer_view
                )
            youTubePlayer.addListener(customPlayerUIController)
            live_youtube_plyer_view.addFullScreenListener(customPlayerUIController)

            youTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    youTubePlayer.loadVideo(videoIds[0], 0F)
                }
            })

        }, true)
    }
}
