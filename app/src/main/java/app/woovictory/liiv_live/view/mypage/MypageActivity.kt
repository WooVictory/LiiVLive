package app.woovictory.liiv_live.view.mypage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import app.woovictory.liiv_live.Get.GetMypageResponse
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.view.check.CheckActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_mypage.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            mypage_x_btn-> finish()
            check_rl -> startActivity<CheckActivity>()
        }
    }

    lateinit var networkService: NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        mypage_x_btn.setOnClickListener(this)
        check_rl.setOnClickListener(this)

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
