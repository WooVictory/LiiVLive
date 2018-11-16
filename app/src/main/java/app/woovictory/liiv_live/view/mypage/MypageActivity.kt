package app.woovictory.liiv_live.view.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.woovictory.liiv_live.Get.GetMypageResponse
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_mypage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageActivity : AppCompatActivity() {

    lateinit var networkService: NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        networkService = ApplicationController.instance.networkService
        var id = SharedPreferenceController.getMyId(this)


        getUserMyapge(id)
    }

    fun getUserMyapge(userId : String){

        var mypageResponse = networkService.getMyPageMain(userId)
        mypageResponse.enqueue(object : Callback<GetMypageResponse>{
            override fun onFailure(call: Call<GetMypageResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<GetMypageResponse>, response: Response<GetMypageResponse>) {
                if(response!!.isSuccessful){
                    Log.v("woo 554 : ", response.body()!!.msg)
                    Log.v("woo 554 : ", response.body()!!.data.img)
                    Glide.with(this@MypageActivity).load(response!!.body()!!.data.img).into(mypage_profile_image)
                    mypage_nick.text = response!!.body()!!.data.nickname
                    mypage_id.text = response!!.body()!!.data.id

                }
            }

        })


    }
}
