package app.woovictory.liiv_live.view.exchange

import android.graphics.Color
import android.graphics.Point
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.util.dialoog.ExchangeDialog
import kotlinx.android.synthetic.main.activity_exchage.*

class ExchageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            exchangeCompleteBtn -> {
                var exchange_dialog = ExchangeDialog(this)


                // 다이얼로그의 백그라운드를 투명으로 만듦.
                exchange_dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                // 다이얼로그를 보여줌.
                //exchange_dialog.window.setGravity(Gravity.BOTTOM)
                exchange_dialog.show()
                // 다이얼로그를 띄우면 dialog class는 화면의 폭이나 높이가 default로 고정되어 있기 때문에
                // 아래의 방법을 써서 dialog의 window를 구해서 임의로 크기 값을 조정해준다.


                // 디스플레이의 해상도를 가지고 온다.
                var display = windowManager.defaultDisplay
                var size = Point()
                display.getSize(size)
                Log.v("woo size : ", display.getSize(size).toString())

                // 비율에 맞게 다이얼로그 크기를 조정.
                var window: Window = exchange_dialog.window

                var x = (size.x * 0.8f).toInt()
                // 이 값을 조정하고 bottom으로 해야지 정중앙으로 배치가 됨...
                var y = (size.y * 0.6f).toInt()
                window.setGravity(Gravity.BOTTOM)


                window.setLayout(x, y)

                //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT-1300, WindowManager.LayoutParams.WRAP_CONTENT)
            }
            exchangeXBtn -> finish()
        }
    }

    fun init() {
        exchange_stock_layout.background.setColorFilter(
                ContextCompat.getColor(this, R.color.mainColor),
                PorterDuff.Mode.SRC_IN
        )
        //setBackgroundColor(ContextCompat.getColor(this, R.color.mainColor))
        exchange_stock_text.setTextColor(ContextCompat.getColor(this, R.color.white))

        exchangeCompleteBtn.setOnClickListener(this)
        exchangeXBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchage)

        init()
    }
}
