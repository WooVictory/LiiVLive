package app.woovictory.liiv_live.view.stock

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.activity_stock_and_fund.*

class StockAndFundActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            stockXBtn -> finish()
            goStockStartBtn -> {
                var intent = packageManager.getLaunchIntentForPackage("com.kbsec.mts.iplustarngm2")
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_and_fund)
        stockXBtn.setOnClickListener(this)
        goStockStartBtn.setOnClickListener(this)
    }
}
