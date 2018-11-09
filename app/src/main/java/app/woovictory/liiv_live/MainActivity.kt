package app.woovictory.liiv_live

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val API_KEY = "AIzaSyBGI5VnYtgjFppgs8eccmktnBTJoe8Q8wQ"
    private val VIDEO_CODE = "98-1s3ls26c"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player.initialize(API_KEY, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(provide: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, b: Boolean) {
                if(!b){
                    youtubePlayer!!.loadVideo(VIDEO_CODE)
                    youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)

                }
            }

            override fun onInitializationFailure(provide: YouTubePlayer.Provider?, youtubeInitializationResult: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext, youtubeInitializationResult!!.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }
}
