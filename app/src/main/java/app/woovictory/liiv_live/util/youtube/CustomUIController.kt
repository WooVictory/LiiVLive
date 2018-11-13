package app.woovictory.liiv_live.util.youtube

import android.view.View
import android.widget.Button
import app.woovictory.liiv_live.R
import com.google.android.youtube.player.YouTubePlayer

class CustomUIController(private val playerUI: View, private val youTubePlayer: YouTubePlayer) {
    private var playing = false
    private var playPauseButton: Button? = null


    init {
        initViews(playerUI)
    }

    private fun initViews(playerUI: View) {
        playPauseButton = playerUI.findViewById(R.id.play_pause_button)

        playPauseButton!!.setOnClickListener {
            run {
                if (playing)
                    this.youTubePlayer.pause()
                else
                    this.youTubePlayer.play()
                this.playing = !this.playing
            }
        }

    }
}
