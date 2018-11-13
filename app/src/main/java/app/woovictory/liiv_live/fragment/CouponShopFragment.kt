package app.woovictory.liiv_live.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.fragment_coupon_home.*
import kotlinx.android.synthetic.main.fragment_coupon_home.view.*

/**
 * Created by VictoryWoo
 */
class CouponShopFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
       /*     goToCoffee->{
                var fragment = HomeStepOneFragment()
                var fm = childFragmentManager
                var transaction = fm.beginTransaction()
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                transaction.addToBackStack(null)
                transaction.add(R.id.couponContainer,fragment).commit()
            }*/
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_coupon_home, container, false)

        view.goToCoffee.setOnClickListener(this)
        return view
    }
}