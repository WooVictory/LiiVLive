package app.woovictory.liiv_live.view.coupon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.widget.RelativeLayout
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.fragment.CouponShopFragment
import app.woovictory.liiv_live.fragment.HomeStepOneFragment
import app.woovictory.liiv_live.fragment.HomeStepTwoFragment
import kotlinx.android.synthetic.main.activity_coupon_shop.*
import kotlinx.android.synthetic.main.fragment_coupon_home.*

class CouponShopActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            /*goToCoffee->{
                var fragment = HomeStepOneFragment()
                var fm = supportFragmentManager
                var transaction = fm.beginTransaction()
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                transaction.addToBackStack(null)
                transaction.add(R.id.couponContainer,fragment).commit()
            }*/
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_shop)

        //goToCoffee.setOnClickListener(this)
        addFragment(CouponShopFragment())


    }


    fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.couponContainer, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.couponContainer, fragment)
        transaction.commit()
    }
}
