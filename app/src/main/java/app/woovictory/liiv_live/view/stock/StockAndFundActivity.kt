package app.woovictory.liiv_live.view.stock

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.activity_stock_and_fund.*

class StockAndFundActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            stockXBtn -> finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_and_fund)
        stockXBtn.setOnClickListener(this)
    }
}
