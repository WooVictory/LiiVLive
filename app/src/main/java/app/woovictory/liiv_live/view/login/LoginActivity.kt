package app.woovictory.liiv_live.view.login

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.Post.PostLoginResponse
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.view.home.HomeActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v!!) {
            loginBtn -> {
                requestLogin()
            }
            goToSignUp -> startActivity<KbLoginActivity>()
            loginKaKaoBtn -> startActivity<KbLoginActivity>()
            loginFacebookBtn -> startActivity<KbLoginActivity>()
            loginGoogleBtn -> startActivity<KbLoginActivity>()
            loginKbBtn -> startActivity<KbLoginActivity>()
        }
    }

    fun init() {
        loginBtn.setOnClickListener(this)
        goToSignUp.setOnClickListener(this)
        loginKaKaoBtn.setOnClickListener(this)
        loginFacebookBtn.setOnClickListener(this)
        loginGoogleBtn.setOnClickListener(this)
        loginKbBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()

        FirebaseMessaging.getInstance().subscribeToTopic("notice");

        Log.v("TATATATA", FirebaseInstanceId.getInstance().getToken())

        loginId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               /* if (s!!.toString().length == 0) {
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }*/

                if(loginId.text.toString().length == 0 && loginPw.text.toString().length == 0){
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(loginId.text.toString().length > 1 && loginPw.text.toString().length >1){
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.greyblue),
                        PorterDuff.Mode.SRC_IN
                    )
                }
                /*if (s!!.length != null) {
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.greyblue),
                        PorterDuff.Mode.SRC_IN
                    )
                } else {
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }*/
            }

        })

        loginPw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(loginId.text.toString().length == 0 && loginPw.text.toString().length == 0){
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }
             /*   if (s!!.toString().length == 0){
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }*/
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(loginId.text.toString().length > 1 && loginPw.text.toString().length >1){
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.greyblue),
                        PorterDuff.Mode.SRC_IN
                    )
                }
              /*  if (s!!.length != null) {
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.greyblue),
                        PorterDuff.Mode.SRC_IN
                    )
                } else {
                    loginBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }*/

            }

        })
    }

    private fun requestLogin(){

//        var idText = loginId.text.toString()
//        var pwText = loginPw.text.toString()

        var idText = "milkho11"
        var pwText = "0465"
        // id가 작성돼있지 않을 때
        if (idText.length <= 0) {
            toast("ID를 작성해주세요!")
        } // PW가 작성돼 있지않을 때
        else if (pwText.length <= 0){
            toast("PW를 작성해주세요")
        } // ID, PW 비어있지 않을 때
        else{
            val networkService : NetworkService = ApplicationController.instance.networkService
            val postLoginResponse = networkService.postLoginResponse(idText, pwText)
            postLoginResponse.enqueue(object : Callback<PostLoginResponse> {
                override fun onFailure(call: Call<PostLoginResponse>?, t: Throwable?) {
                    Log.e("로그인 통신 실패", t.toString())
                }
                override fun onResponse(call: Call<PostLoginResponse>?, response: Response<PostLoginResponse>?) {
                    response?.let {
                        if (response.isSuccessful){
                            if(response.body()!!.message == "로그인 완료"){
                                SharedPreferenceController.clearSPC(applicationContext)
                                SharedPreferenceController.setMyId(applicationContext, response.body()!!.data[0].id)
                                SharedPreferenceController.setMyImage(applicationContext, response.body()!!.data[0].img)
                                SharedPreferenceController.setMyPoint(applicationContext, 3000)
                                startActivity<HomeActivity>()
                                finish()
                            } // 로그인 실패
                            else if (response.body()!!.message == "PW 틀림"){
                                toast("PW 틀림")
                            }else {
                                toast("존재하는 아이디 없음")
                            }
                        }
                    }
                }
            })
        }
    }
}
