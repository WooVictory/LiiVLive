package app.woovictory.liiv_live.view.sign

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.activity_kb_sign.*
import kotlinx.android.synthetic.main.activity_login.*

class KbSignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kb_sign)

        kbSignId.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(kbSignId.text.toString().length == 0 && kbSignPw.text.toString().length == 0 && kbSignNickname.text.toString().length == 0){
                    kbSignBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(kbSignId.text.toString().length >1 && kbSignPw.text.toString().length >1
                    && kbSignNickname.text.toString().length >1){
                    kbSignBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.greyblue),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }

        })
        kbSignPw.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(kbSignId.text.toString().length == 0 && kbSignPw.text.toString().length == 0 &&
                    kbSignNickname.text.toString().length == 0){
                    kbSignBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(kbSignId.text.toString().length >1 && kbSignPw.text.toString().length >1
                    && kbSignNickname.text.toString().length >1){
                    kbSignBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.greyblue),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }

        })
        kbSignNickname.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(kbSignId.text.toString().length == 0 && kbSignPw.text.toString().length == 0 &&
                    kbSignNickname.text.toString().length == 0){
                    kbSignBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.viewColor),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(kbSignId.text.toString().length >1 && kbSignPw.text.toString().length >1
                    && kbSignNickname.text.toString().length >1){
                    kbSignBtn.background.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.greyblue),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }

        })



    }
}
