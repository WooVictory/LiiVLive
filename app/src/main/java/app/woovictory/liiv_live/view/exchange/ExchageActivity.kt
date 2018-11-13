package app.woovictory.liiv_live.view.exchange

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import android.view.WindowManager
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.util.dialoog.ExchangeDialog
import kotlinx.android.synthetic.main.activity_exchage.*

class ExchageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            exchangeCompleteBtn->{
                var exchange_dialog = ExchangeDialog(this)


                // 다이얼로그의 백그라운드를 투명으로 만듦.
                exchange_dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                // 다이얼로그를 보여줌.
                exchange_dialog.show()
                // 다이얼로그를 띄우면 dialog class는 화면의 폭이나 높이가 default로 고정되어 있기 때문에
                // 아래의 방법을 써서 dialog의 window를 구해서 임의로 크기 값을 조정해준다.
                var window: Window = exchange_dialog.window
                window.setLayout(1100, WindowManager.LayoutParams.WRAP_CONTENT)
            }
        }
    }

    fun init(){
        exchange_stock_layout.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),PorterDuff.Mode.SRC_IN)
            //setBackgroundColor(ContextCompat.getColor(this, R.color.mainColor))
        exchange_stock_text.setTextColor(ContextCompat.getColor(this, R.color.white))

        exchangeCompleteBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchage)

        init()
    }
}
