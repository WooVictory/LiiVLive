package app.woovictory.liiv_live.view.coupon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.CoffeeAdapter
import app.woovictory.liiv_live.model.CoffeeData
import app.woovictory.liiv_live.util.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_coupon_coffee.*

class CouponCoffeeActivity : AppCompatActivity() {

    lateinit var coffeeAdapter: CoffeeAdapter
    lateinit var item_list : ArrayList<CoffeeData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_coffee)

        item_list = ArrayList()

        for(i in 0..10)
            item_list.add(CoffeeData(R.drawable.americano_img,"스타벅스","HOT) 아메리카노","4,100원"))

        coffeeAdapter = CoffeeAdapter(item_list, this)

        coffee_rv.setHasFixedSize(true)
        coffee_rv.layoutManager = GridLayoutManager(this, 2)
        coffee_rv.adapter = coffeeAdapter

        val spanCount = 2 // 3 columns
        val spacing = 40 // 50px
        val includeEdge = true
        coffee_rv.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
    }
}