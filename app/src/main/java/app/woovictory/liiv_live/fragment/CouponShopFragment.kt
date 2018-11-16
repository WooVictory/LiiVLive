package app.woovictory.liiv_live.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.coupon.CouponCoffeeActivity
import kotlinx.android.synthetic.main.activity_coupon_coffee.*
import kotlinx.android.synthetic.main.activity_coupon_coffee.view.*
import kotlinx.android.synthetic.main.fragment_coupon_home.*
import kotlinx.android.synthetic.main.fragment_coupon_home.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by VictoryWoo
 */
class CouponShopFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            goToCoffee -> {
                context!!.startActivity<CouponCoffeeActivity>()

                /*FIXME
                * 이 부분이 프래그먼트 교체를 할 때 animation을 주는 효과이다.
                * 오른쪽에서 왼쪽으로 등장한다.
                * */
                /*var fragment = HomeStepOneFragment()
                var fm = childFragmentManager
                var transaction = fm.beginTransaction()
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                transaction.addToBackStack(null)
                transaction.add(R.id.frame,fragment).commit()*/

            }
            coupon_coffee_x_btn -> activity!!.finish()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_coupon_home, container, false)

        view.goToCoffee.setOnClickListener(this)
        view.coupon_coffee_x_btn.setOnClickListener(this)
        return view
    }
}