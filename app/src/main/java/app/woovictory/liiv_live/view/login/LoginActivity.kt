package app.woovictory.liiv_live.view.login

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            loginBtn -> {
                startActivity<HomeActivity>()
                finish()
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
}
