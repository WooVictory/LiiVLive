package app.woovictory.liiv_live.view.exchange

import android.graphics.Color
import android.graphics.Point
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.PointreeHistoryData
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.db.pointItemDataList
import app.woovictory.liiv_live.util.dialog.ExchangeDialog
import kotlinx.android.synthetic.main.activity_exchage.*
import org.jetbrains.anko.toast

class ExchageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            exchangeCompleteBtn -> {

                // money가 포인트보다 적을 때
                if(SharedPreferenceController.getMyPoint(applicationContext) > (exchange_will_use_pointree.text.toString()).toInt()){
                    money = (exchange_will_use_pointree.text.toString()).toInt()
                    pointItemDataList.addPointItemData("환전하기",-money,"차감")
                    SharedPreferenceController.setMyPoint(this,
                        SharedPreferenceController.getMyPoint(this@ExchageActivity)-money)
                    var exchange_dialog = ExchangeDialog(this)

                    exchange_my_point.text = SharedPreferenceController.getMyPoint(applicationContext).toString() + " P"
                    posible_exchange_point.text = SharedPreferenceController.getMyPoint(applicationContext).toString() + " P"

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
                }
                // money가 더 클 경우
                else{
                    toast("포인트가 부족합니다." +
                            "")
                }


                //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT-1300, WindowManager.LayoutParams.WRAP_CONTENT)
            }
            exchangeXBtn -> finish()
            exchange_chen -> {
                exchange_will_use_pointree.setText("1000")
                money = 1000

            }
            exchange_ten_man -> {
                exchange_will_use_pointree.setText("100000")
                money = 100000
            }
            exchange_man -> {
                exchange_will_use_pointree.setText("10000")
                money = 10000

            }
            exchange_stock_layout1->{
                if(!exchange_stock_layout1.isSelected){
                    exchange_stock_layout1.isSelected = true
                    exchange_stock_layout1.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),
                        PorterDuff.Mode.SRC_IN)
                    exchange_stock_layout2.isSelected = false
                    exchange_stock_layout3.isSelected = false
                }else{
                    exchange_stock_layout1.isSelected = false
                    exchange_stock_layout1.background = getDrawable(R.drawable.border_button_templete)
                }

            }
            exchange_stock_layout2->{
                if(!exchange_stock_layout2.isSelected){
                    exchange_stock_layout2.isSelected = true
                    exchange_stock_layout2.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),
                        PorterDuff.Mode.SRC_IN)
                    exchange_stock_layout1.isSelected = false
                    exchange_stock_layout3.isSelected = false
                }else{
                    exchange_stock_layout2.isSelected = false
                    exchange_stock_layout2.background = getDrawable(R.drawable.border_button_templete)
                }

            }
            exchange_stock_layout3->{
                if(!exchange_stock_layout3.isSelected){
                    exchange_stock_layout3.isSelected = true
                    exchange_stock_layout3.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),
                        PorterDuff.Mode.SRC_IN)
                    exchange_stock_layout1.isSelected = false
                    exchange_stock_layout2.isSelected = false
                }else{
                    exchange_stock_layout3.isSelected = false
                    exchange_stock_layout3.background = getDrawable(R.drawable.border_button_templete)
                }
            }

        }
    }

    fun init() {
       /* exchange_stock_layout2.background.setColorFilter(
            ContextCompat.getColor(this, R.color.mainColor),
            PorterDuff.Mode.SRC_IN
        )*/
        //setBackgroundColor(ContextCompat.getColor(this, R.color.mainColor))
        //  exchange_stock_text.setTextColor(ContextCompat.getColor(this, R.color.white))

        exchangeCompleteBtn.setOnClickListener(this)
        exchangeXBtn.setOnClickListener(this)
        exchange_ten_man.setOnClickListener(this)
        exchange_man.setOnClickListener(this)
        exchange_chen.setOnClickListener(this)
        networkService = ApplicationController.instance.networkService
        exchange_my_point.text = SharedPreferenceController.getMyPoint(this@ExchageActivity).toString() + " P"
        posible_exchange_point.text = SharedPreferenceController.getMyPoint(applicationContext).toString()  + " P"
        exchange_stock_layout1.setOnClickListener(this)
        exchange_stock_layout2.setOnClickListener(this)
        exchange_stock_layout3.setOnClickListener(this)

    }

    lateinit var networkService: NetworkService
    var money : Int = 0
    lateinit var item_list : ArrayList<PointreeHistoryData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchage)

        init()
    }
}
