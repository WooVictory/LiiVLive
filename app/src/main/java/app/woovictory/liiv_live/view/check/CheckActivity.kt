package app.woovictory.liiv_live.view.check

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
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.db.pointItemDataList
import app.woovictory.liiv_live.util.dialog.CheckCompleteDialog
import kotlinx.android.synthetic.main.activity_check.*

class CheckActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            check_back_btn -> finish()
            check_okay_btn->{
                if(!flag){
                    flag = true
                    check_calendar_image.setImageResource(R.drawable.calendar_check)
                    check_okay_btn.background.setColorFilter(ContextCompat.getColor(this, R.color.viewColor),PorterDuff.Mode.SRC_IN)
                    check_okay_btn.isClickable = false
                    check_btn_text.text = "출석 완료"
                    pointItemDataList.check_flag = 1

                    pointItemDataList.addPointItemData("출석체크",30,"획득")
                    SharedPreferenceController.setMyPoint(this,
                        SharedPreferenceController.getMyPoint(this@CheckActivity)+30)

                    var check_dialog = CheckCompleteDialog(this@CheckActivity)
                    check_dialog.setCanceledOnTouchOutside(false)
                    check_dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    check_dialog.show()

                    var display = windowManager.defaultDisplay
                    var size = Point()
                    display.getSize(size)
                    Log.v("woo size : ", display.getSize(size).toString())

                    // 비율에 맞게 다이얼로그 크기를 조정.
                    var window: Window = check_dialog.window

                    var x = (size.x * 0.8f).toInt()
                    // 이 값을 조정하고 bottom으로 해야지 정중앙으로 배치가 됨...
                    var y = (size.y * 0.6f).toInt()
                    window.setGravity(Gravity.BOTTOM)

                    window.setLayout(x, y)
                }
            }

        }
    }

    var flag : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)


        // 출석체크 화면에서 닉네임 쉐어드에서 꺼내서 넣기.
        var nickname = SharedPreferenceController.getMyNick(this@CheckActivity)
        check_nickname.text = nickname

        if(pointItemDataList.check_flag == 1){
            check_calendar_image.setImageResource(R.drawable.calendar_check)
            check_okay_btn.background.setColorFilter(ContextCompat.getColor(this, R.color.viewColor),PorterDuff.Mode.SRC_IN)
            check_okay_btn.isClickable = false
            check_btn_text.text = "출석 완료"
        }

        check_back_btn.setOnClickListener(this)
        check_okay_btn.setOnClickListener(this)
    }
}
