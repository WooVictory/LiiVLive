package app.woovictory.liiv_live.view.live

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import app.woovictory.liiv_live.Get.getYoutubeResponse
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.LivePagerAdapter
import app.woovictory.liiv_live.util.youtube.CustomPlayerUIController
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_live.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LiveActivity : AppCompatActivity() {

    private val videoIds = arrayOf("q89V7EDOIa8", "rkKQxHoPiTs")
    lateinit var networkService: NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        getYoutubeUrl()


        live_vp.adapter = LivePagerAdapter(supportFragmentManager)
        live_vp.setCurrentItem(0)

//        live_indicator.setItemMargin(10)
//        live_indicator.setAnimDuration(300)
//        live_indicator.createDotPanel(2, R.drawable.dot_non, R.drawable.dot)
//
//        live_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//            override fun onPageSelected(position: Int) {
//                live_indicator.selectDot(position)
//            }
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//            }
//        })


    }

    fun getYoutubeUrl(){
        networkService = ApplicationController.instance.networkService
        var youtubeResponse = networkService.getYoutubeUrl()
        youtubeResponse.enqueue(object : Callback<getYoutubeResponse>{
            override fun onFailure(call: Call<getYoutubeResponse>, t: Throwable) {
                Log.v("woo TAG : ", t.message)
            }

            override fun onResponse(call: Call<getYoutubeResponse>, response: Response<getYoutubeResponse>) {
                if(response!!.isSuccessful){
                    videoIds[0] = response!!.body()!!.data[0].youtube_id
                    Log.v("woo TAG 533 : ",videoIds[0])

                    lifecycle.addObserver(live_youtube_plyer_view)

                    val customPlayerUI = live_youtube_plyer_view.inflateCustomPlayerUI(R.layout.custom_play_button)

                    live_youtube_plyer_view.initialize({ youTubePlayer ->

                        val customPlayerUIController =
                            CustomPlayerUIController(
                                this@LiveActivity,
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

        })

    }
}
