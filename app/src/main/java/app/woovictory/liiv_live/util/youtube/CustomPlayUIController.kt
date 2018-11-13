package app.woovictory.liiv_live.util.youtube

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


import app.woovictory.liiv_live.R
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener


internal class CustomPlayerUIController(
    private val context: Context,
    private val playerUI: View,
    private val youTubePlayer: YouTubePlayer,
    private val youTubePlayerView: YouTubePlayerView
) : AbstractYouTubePlayerListener(), YouTubePlayerFullScreenListener {

    // panel is used to intercept clicks on the WebView, I don't want the user to be able to click the WebView directly.
    private var panel: View? = null
    private var progressbar: View? = null
    private var videoCurrentTimeTextView: TextView? = null
    private var videoDurationTextView: TextView? = null
    private var playPauseButton: Button? = null
    private var enterExitFullscreenButton: Button? = null

    private var playing = true
    private var fullscreen = false

    init {

        initViews(playerUI)
    }

    private fun initViews(playerUI: View) {
        panel = playerUI.findViewById(R.id.panel)
        progressbar = playerUI.findViewById(R.id.progressbar)
        videoCurrentTimeTextView = playerUI.findViewById(R.id.video_current_time)
        videoDurationTextView = playerUI.findViewById(R.id.video_duration)
        playPauseButton = playerUI.findViewById(R.id.play_pause_button)
        enterExitFullscreenButton = playerUI.findViewById(R.id.enter_exit_fullscreen_button)

        playPauseButton!!.setOnClickListener { view ->
            if (playing)
                youTubePlayer.pause()
            else
                youTubePlayer.play()

            playing = !playing
        }

        enterExitFullscreenButton!!.setOnClickListener { view ->
            if (fullscreen)
                youTubePlayerView.exitFullScreen()
            else
                youTubePlayerView.enterFullScreen()

            fullscreen = !fullscreen
        }
    }

    override fun onReady() {
        progressbar!!.visibility = View.GONE
    }

    override fun onStateChange(state: PlayerConstants.PlayerState) {
        if (state == PlayerConstants.PlayerState.PLAYING || state == PlayerConstants.PlayerState.PAUSED || state == PlayerConstants.PlayerState.VIDEO_CUED)
            panel!!.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        else if (state == PlayerConstants.PlayerState.BUFFERING)
            panel!!.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
    }

    @SuppressLint("SetTextI18n")
    override fun onCurrentSecond(second: Float) {
        videoCurrentTimeTextView!!.text = second.toString() + ""
    }

    @SuppressLint("SetTextI18n")
    override fun onVideoDuration(duration: Float) {
        videoDurationTextView!!.text = duration.toString() + ""
    }

    override fun onYouTubePlayerEnterFullScreen() {
        val viewParams = playerUI.layoutParams
        viewParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        viewParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        playerUI.layoutParams = viewParams
    }

    override fun onYouTubePlayerExitFullScreen() {
        val viewParams = playerUI.layoutParams
        viewParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        viewParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        playerUI.layoutParams = viewParams
    }
}