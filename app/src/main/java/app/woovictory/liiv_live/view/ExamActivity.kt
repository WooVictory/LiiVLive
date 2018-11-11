package app.woovictory.liiv_live.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.util.CustomPlayerUIController
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_exam.*


class ExamActivity : AppCompatActivity() {

    private val videoIds = arrayOf("98-1s3ls26c", "98-1s3ls26c")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

        lifecycle.addObserver(youtube_plyer_view)

        val customPlayerUI = youtube_plyer_view.inflateCustomPlayerUI(R.layout.custom_play_button)

        youtube_plyer_view.initialize({ youTubePlayer ->

            val customPlayerUIController =
                CustomPlayerUIController(this, customPlayerUI, youTubePlayer, youtube_plyer_view)
            youTubePlayer.addListener(customPlayerUIController)
            youtube_plyer_view.addFullScreenListener(customPlayerUIController)

            youTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    youTubePlayer.loadVideo(videoIds[0], 0F)
                }
            })

        }, true)
    }
}
