package app.woovictory.liiv_live.view.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.sign.KbSignActivity
import kotlinx.android.synthetic.main.activity_kb_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class KbLoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            loginKBSignBtn -> startActivity<KbSignActivity>()
            loginStarBangkingBtn -> toast("준비 중입니다:)")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kb_login)

        loginKBSignBtn.setOnClickListener(this)
        loginStarBangkingBtn.setOnClickListener(this)
    }
}
