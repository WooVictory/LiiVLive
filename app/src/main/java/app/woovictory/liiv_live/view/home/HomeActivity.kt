package app.woovictory.liiv_live.view.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import app.woovictory.liiv_live.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import android.os.Build
import android.annotation.TargetApi
import android.app.ActionBar
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import app.woovictory.liiv_live.MainActivity
import app.woovictory.liiv_live.adapter.HomeFragmentAdapter
import app.woovictory.liiv_live.adapter.NaviAdapter
import app.woovictory.liiv_live.view.check.CheckActivity
import app.woovictory.liiv_live.view.pointree.PointreeHistoryActivity
import app.woovictory.liiv_live.view.quiz.QuizReviewActivity
import app.woovictory.liiv_live.view.survey.SurveyActivity
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.sliding_layout.*
import org.jetbrains.anko.startActivity
import android.widget.RelativeLayout
import app.woovictory.liiv_live.util.dialoog.SurveyDialog
import app.woovictory.liiv_live.view.coupon.CouponShopActivity
import app.woovictory.liiv_live.view.exchange.ExchageActivity
import app.woovictory.liiv_live.view.mypage.MypageActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.jetbrains.anko.toast


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            checkLayout -> startActivity<CheckActivity>()
            quizLayout -> startActivity<QuizReviewActivity>()
            surveyLayout -> startActivity<SurveyActivity>()
            participantBtn -> {
                //startActivity<MainActivity>()
                val survey_dialog = SurveyDialog(this@HomeActivity)
                /*   var params : WindowManager.LayoutParams = survey_dialog.window!!.attributes
                   params.width = WindowManager.LayoutParams.MATCH_PARENT
                   params.height = WindowManager.LayoutParams.MATCH_PARENT

                   survey_dialog.window.attributes = params
                   survey_dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                   survey_dialog.setCanceledOnTouchOutside(true)*/
                survey_dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                survey_dialog.show()
                var window: Window = survey_dialog.window
                window.setLayout(1300, WindowManager.LayoutParams.WRAP_CONTENT)
            }
            topLayout -> startActivity<PointreeHistoryActivity>()
            pointree_go_btn -> startActivity<PointreeHistoryActivity>()
            homeGoToMypageBtn -> startActivity<MypageActivity>()
            goToExchangeBtn -> startActivity<ExchageActivity>()
            goToCouponShopBtn -> startActivity<CouponShopActivity>()
            /*sliding_up_panel_layout->{
                toast("들어오니111?")
                if(sliding_up_panel_layout.anchorPoint == 1f){
                    toast("들어오니?")
                    val plControl = sliding_tops.getLayoutParams() as LinearLayout.LayoutParams

                    plControl.leftMargin = 0
                    plControl.rightMargin =0

                    sliding_tops.layoutParams = plControl
                    toast("마무리 되니??")
                }
            }*/

        }


    }


    lateinit var naviAdapter: NaviAdapter

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1) //call this before super.onCreate
    private fun forceRtlIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }

    fun init() {
        checkLayout.setOnClickListener(this)
        quizLayout.setOnClickListener(this)
        surveyLayout.setOnClickListener(this)
        participantBtn.setOnClickListener(this)
        topLayout.setOnClickListener(this)
        pointree_go_btn.setOnClickListener(this)
        sliding_up_panel_layout.setOnClickListener(this)
        homeGoToMypageBtn.setOnClickListener(this)
        goToExchangeBtn.setOnClickListener(this)
        goToCouponShopBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        init()

        if (sliding_up_panel_layout.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            toast("올라갔음")
        }
        Log.v("woo 119", sliding_up_panel_layout.panelState.toString())


        //init()


        main_viewpager.adapter = HomeFragmentAdapter(supportFragmentManager)
        main_viewpager.setCurrentItem(0)

        circleAnimIndicator.setItemMargin(10)
        circleAnimIndicator.setAnimDuration(300)
        circleAnimIndicator.createDotPanel(2, R.drawable.dot_non, R.drawable.dot)

        var mPageChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                circleAnimIndicator.selectDot(position)
            }

        }

        main_viewpager.addOnPageChangeListener(mPageChangeListener)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        toggle.setDrawerIndicatorEnabled(false)
        val drawable =
            ResourcesCompat.getDrawable(resources, R.drawable.main_drawer_bar_btn, applicationContext!!.getTheme())

        //val bitmap = (drawable as BitmapDrawable).bitmap
        //val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        // forceRtlIfSupported()

        naviAdapter = NaviAdapter()

        //navigationmenu.adapter = naviAdapter
        var point: String = "포인트리 얻기"


        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
