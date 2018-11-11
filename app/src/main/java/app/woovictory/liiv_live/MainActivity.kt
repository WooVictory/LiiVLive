package app.woovictory.liiv_live

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : YouTubeBaseActivity(), View.OnClickListener, YouTubePlayer.OnInitializedListener {


    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, b: Boolean) {

        if (null == youtubePlayer) return;
        mPlayer = youtubePlayer!!

        displayCurrentTime();
        if (!b) {
            youtubePlayer!!.loadVideo(VIDEO_CODE)
            youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)

            youtubePlayer.setPlaybackEventListener(playbackEventListener)
            youtubePlayer.setPlayerStateChangeListener(playStateChangeListener)

        }
    }

    private val runnable = object : Runnable {
        override fun run() {
            //displayCurrentTime()
            handler.postDelayed(this, 100)
        }
    }

    var playStateChangeListener : YouTubePlayer.PlayerStateChangeListener =
        object : YouTubePlayer.PlayerStateChangeListener{
            override fun onAdStarted() {
            }

            override fun onLoading() {
            }

            override fun onVideoStarted() {
                displayCurrentTime()
            }

            override fun onLoaded(p0: String?) {
            }

            override fun onVideoEnded() {
            }

            override fun onError(p0: YouTubePlayer.ErrorReason?) {
            }

        }



    var playbackEventListener: YouTubePlayer.PlaybackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onPlaying() {
            handler.postDelayed(runnable, 100)
            displayCurrentTime()

        }

        override fun onStopped() {
            handler.removeCallbacks(runnable)
        }

        override fun onPaused() {
            handler.removeCallbacks(runnable)
        }

    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

    }

    override fun onClick(v: View?) {
        when (v!!) {
            playBtn -> {
                if(null != mPlayer && !mPlayer!!.isPlaying){
                    mPlayer!!.play()
                    Log.v("woo 205","눌린거지?")
                    toast("눌린거지?")
                }
            }
            pauseBtn->{
                if(null != mPlayer && mPlayer!!.isPlaying){
                    mPlayer!!.pause()
                    Log.v("woo 205","눌린거지?")
                    toast("눌린거지?")
                }
            }
        }
    }

    private fun formatTime(millis: Int): String {
        val seconds = millis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60

        return (if (hours == 0) "--:" else hours.toString() + ":") + String.format(
            "%02d:%02d",
            minutes % 60,
            seconds % 60
        )
    }

    private fun displayCurrentTime() {
        if (null == mPlayer) return
        val formattedTime = formatTime(mPlayer!!.getDurationMillis() - mPlayer!!.getCurrentTimeMillis())
        //play_time.setText(formattedTime)
    }

    private val API_KEY = "AIzaSyBGI5VnYtgjFppgs8eccmktnBTJoe8Q8wQ"
    private val VIDEO_CODE = "98-1s3ls26c"
    private var mPlayer: YouTubePlayer? = null
    var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playBtn.setOnClickListener(this)
        pauseBtn.setOnClickListener(this)


        var customView : View = YouTubePlayerView.inflate(this,R.layout.custom_play_button,null)

        player.initialize(API_KEY, this)

        /*player.initialize(API_KEY, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(provide: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, b: Boolean) {
                if(!b){
                    youtubePlayer!!.loadVideo(VIDEO_CODE)
                    youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)

                    youtubePlayer.setPlaybackEventListener()

                }
            }

            override fun onInitializationFailure(provide: YouTubePlayer.Provider?, youtubeInitializationResult: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext, youtubeInitializationResult!!.toString(), Toast.LENGTH_LONG).show()
            }

        })*/
    }
}
