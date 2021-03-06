package app.woovictory.liiv_live.view.home

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import app.woovictory.liiv_live.Get.GetUserMainResponse
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.Post.PostRefreshFcmTokenResponse
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.HomeFragmentAdapter
import app.woovictory.liiv_live.adapter.NaviAdapter
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.db.pointItemDataList
import app.woovictory.liiv_live.view.check.CheckActivity
import app.woovictory.liiv_live.view.coupon.CouponShopActivity
import app.woovictory.liiv_live.view.exchange.ExchageActivity
import app.woovictory.liiv_live.view.live.LiveActivity
import app.woovictory.liiv_live.view.mypage.MypageActivity
import app.woovictory.liiv_live.view.pointree.PointreeHistoryActivity
import app.woovictory.liiv_live.view.quiz.QuizReviewActivity
import app.woovictory.liiv_live.view.stock.StockAndFundActivity
import app.woovictory.liiv_live.view.survey.SurveyActivity
import com.bumptech.glide.Glide
import com.google.firebase.iid.FirebaseInstanceId
import com.sendbird.android.SendBird
import com.sendbird.android.SendBirdException
import com.sendbird.android.User
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.sliding_layout.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            checkLayout -> startActivity<CheckActivity>()
            quizLayout -> startActivity<QuizReviewActivity>()
            surveyLayout -> startActivity<SurveyActivity>()
            /*  slide_up_btn -> {
                  if (!click_flag) {
                      click_flag = true
                      custom_scroll_view.setEnableScrolling(true)
                  } else {
                      click_flag = false
                      custom_scroll_view.setEnableScrolling(false)
                  }
              }
              sliding_tops -> {
                  if (!click_flag) {
                      click_flag = true
                      custom_scroll_view.setEnableScrolling(true)
                  } else {
                      click_flag = false
                      custom_scroll_view.setEnableScrolling(false)
                  }
              }*/
            participantBtn -> {
                //startActivity<MainActivity>()


                // 아래의 방법은 또 다른 다이얼로그 띄우는 방법
                /*   var params : WindowManager.LayoutParams = survey_dialog.window!!.attributes
                   params.width = WindowManager.LayoutParams.MATCH_PARENT
                   params.height = WindowManager.LayoutParams.MATCH_PARENT

                   survey_dialog.window.attributes = params
                   survey_dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                   survey_dialog.setCanceledOnTouchOutside(true)*/

                // 밑에가 진퉁 방법임.
                /*      val survey_dialog = SurveyDialog(this@HomeActivity)

                      survey_dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                      survey_dialog.show()

                      var display = windowManager.defaultDisplay
                      var size = Point()

                      display.getSize(size)

                      var x = (size.x * 0.8f).toInt()
                      var y = (size.y * 0.6f).toInt()

                      var window: Window = survey_dialog.window
                      window.setGravity(Gravity.BOTTOM)
                      window.setLayout(x,y)


      */

                //startActivity<LiveActivity>()

                SendBird.init(APP_ID, this)

                var userId = SharedPreferenceController.getMyId(this)

                // userId의 모든 공간 삭제
                userId = userId.replace("\\s", "")
                var nick = SharedPreferenceController.getMyNick(this)

                var userNickname = nick

                connectToSendBird(userId, userNickname)
                //window.setLayout(1300, WindowManager.LayoutParams.WRAP_CONTENT)
            }
            topLayout -> startActivity<PointreeHistoryActivity>()
            pointree_go_btn -> startActivity<CouponShopActivity>()
            homeGoToMypageBtn -> startActivity<MypageActivity>()
            goToExchangeBtn -> startActivity<ExchageActivity>()
            goToCouponShopBtn -> startActivity<CouponShopActivity>()
            goToStockBtn -> startActivity<StockAndFundActivity>()
            goToLive -> startActivity<CouponShopActivity>()
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
        goToStockBtn.setOnClickListener(this)
        goToLive.setOnClickListener(this)
        slide_up_btn.setOnClickListener(this)
        sliding_tops.setOnClickListener(this)
        goToDetailStockBtn.setOnClickListener(this)
    }

    val APP_ID = "2A8C97EE-D8F7-473B-AEA5-B37A877DAB31"
    var click_flag: Boolean = false

    override fun onResume() {
        super.onResume()
        mypage_ponitree.text = SharedPreferenceController.getMyPoint(this@HomeActivity).toString() + " P"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        custom_scroll_view.setEnableScrolling(false)

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        init()

        val random = Random()
        val num = random.nextInt(2)
        redrawHomeAct(num)


        mypage_ponitree.text = SharedPreferenceController.getMyPoint(this@HomeActivity).toString() + " P"

        goToDetailStockBtn.setOnClickListener {
            if (num == 0) {
                startActivity<CouponShopActivity>()
            } else {
                startActivity<StockAndFundActivity>()
            }
        }

        // 네비게이션 바 쉐어드에서 이름 꺼내와서 달기.

        // activity_home의 navigation view를 참조해서
        // 그 navigation view가 포함하고 있는 header_layout을 참조한다.
        // 그리고 그 view를 homeHeaderView라는 변수로 받는다.
        // 그리고 그 하위의 id를 참조한다.
        var homeHeaderView: View = nav_view.getHeaderView(0)
        var nav_nick = homeHeaderView.findViewById<TextView>(R.id.nav_nick)
        var nickname: String = SharedPreferenceController.getMyNick(this@HomeActivity)
        Log.v("woo 1155 : ", nickname.toString())
        nav_nick.text = SharedPreferenceController.getMyNick(this@HomeActivity)


        Log.v("woo 1219 : ", sliding_up_panel_layout.panelState.toString())

        sliding_up_panel_layout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
                Log.v("1330 offset : ", slideOffset.toString())
                if(slideOffset == 1.0f){
                    custom_scroll_view.setEnableScrolling(true)
                }else{
                    custom_scroll_view.setEnableScrolling(false)
                }
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState?
            ) {
                /*Log.v("woo 1229 : ", previousState!!.toString())
                Log.v("woo 1220 : ", newState!!.toString())
                if (previousState!!.toString() == "COLLAPSED" && newState!!.toString() =="DRAGGING")
                    custom_scroll_view.setEnableScrolling(true)
                else
                    custom_scroll_view.setEnableScrolling(false)*/

            }


        })


        // 유저 정보 받아오는 통신
        requestUserMain(SharedPreferenceController.getMyId(applicationContext))
        refreshFcmToekn(
            SharedPreferenceController.getMyId(applicationContext),
            FirebaseInstanceId.getInstance().getToken().toString()
        )
        Log.v("TAG", FirebaseInstanceId.getInstance().getToken().toString() + "   이것은 fcm 토큰이다.")


        slide_up_btn.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event!!.action) {
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_MOVE -> {
                        custom_scroll_view.setEnableScrolling(true)
                        custom_scroll_view.fullScroll(ScrollView.FOCUS_UP)
                    }
                    MotionEvent.ACTION_DOWN -> {
                        custom_scroll_view.setEnableScrolling(false)
                        custom_scroll_view.fullScroll(ScrollView.FOCUS_UP)
                    }
                }
                return true
            }

        })

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

    fun connectToSendBird(userId: String, userNickname: String) {


        SendBird.connect(userId, object : SendBird.ConnectHandler {
            override fun onConnected(user: User?, e: SendBirdException?) {
                if (e != null) {
                    // Error!
                    Log.v("TAG", "connectToSendBird 에러 발생")
                }
                // Update the user's nickname
                updateCurrentUserInfo(userNickname)

                startActivity<LiveActivity>()

            }
        })
    }

    fun updateCurrentUserInfo(userNickName: String) {
        SendBird.updateCurrentUserInfo(userNickName, null, object : SendBird.UserInfoUpdateHandler {
            override fun onUpdated(e: SendBirdException?) {
                if (e != null) {

                    toast("" + e.code + ":" + e.message)

                    return
                }
            }
        })

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

    fun refreshFcmToekn(userID: String, fcmToken: String) {
        val networkService = ApplicationController.instance.networkService
        val postRefreshFcmTokenResponse = networkService.postRefreshFcmTokenResponse(userID, fcmToken)

        postRefreshFcmTokenResponse.enqueue(object : Callback<PostRefreshFcmTokenResponse> {
            override fun onResponse(
                call: Call<PostRefreshFcmTokenResponse>,
                response: Response<PostRefreshFcmTokenResponse>
            ) {
                if (response.isSuccessful) {
                    SharedPreferenceController.setMyFcmToken(applicationContext, fcmToken)
                    Log.v("fcmToken =", fcmToken)
                }
            }

            override fun onFailure(call: Call<PostRefreshFcmTokenResponse>, t: Throwable) {
                Log.e("FCM REFRESH 통신 실패", t.toString())
            }
        })
    }

    fun requestUserMain(userID: String) {
        Log.v("TAG", "들어오니?")

        var userID = userID
        var networkService: NetworkService = ApplicationController.instance.networkService

        var GetUserMainResponse = networkService.getUserMain(userID)

        GetUserMainResponse.enqueue(object : Callback<GetUserMainResponse> {
            override fun onFailure(call: Call<GetUserMainResponse>?, t: Throwable?) {
                Log.v("TAG", "유저 메인 통신 실패")
            }

            override fun onResponse(call: Call<GetUserMainResponse>?, response: Response<GetUserMainResponse>?) {
                if (response!!.isSuccessful) {
                    // 유저 아이디 : String
                    Log.v("TAG 539", response.body()!!.data.id)
                    home_nick.text = response.body()!!.data.id

                    // img : String
                    Log.v("TAG", response.body()!!.data.img)
                    Glide.with(this@HomeActivity).load(response.body()!!.data.img).into(main_image)

                    // level : Int
                    Log.v("TAG", response.body()!!.data.level.toString())
                    pointItemDataList.level = response.body()!!.data.level.toString()

                    // nickname : String
                    Log.v("TAG", response.body()!!.data.nickname)
                    home_nick.text = response.body()!!.data.nickname
                    email_tv.text= SharedPreferenceController.getMyId(applicationContext)
                }
            }
        })
    }

    fun redrawHomeAct(flag: Int) {
        if (flag == 0) {
            sliding_text_one.text = "쿠폰샵"
            two_tv.text = "에서"
            three_tv.text = "다양한 상품"
            four_tv.text = "을 구입해보세요!"
            five_tv.text = "편의점부터 브랜드샵까지!"

            six_icon.setImageResource(R.drawable.coupon_shop_coupon_icon)
            seven_tv.text = "쿠폰샵 BEST 상품"
            eight_iv.setImageResource(R.drawable.starbucks_americano)
            eight_tv.text = "스타벅스..."
            nine_iv.setImageResource(R.drawable.lotte_pepero)
            nine_tv.text = "롯데)빼빼로"
            ten_iv.setImageResource(R.drawable.ediya_cafe_latte)
            ten_tv.text = "이디야 카.."
        } else {
            sliding_text_one.text = "포인트리"
            two_tv.text = "를 이용해"
            three_tv.text = "작은 주식"
            four_tv.text = "을 시작해보세요!"
            five_tv.text = "편의점부터 브랜드샵까지!"

            six_icon.setImageResource(R.drawable.main_slide_best_pointree_icon)
            seven_tv.text = "BEST 포인트리 주식"
            eight_iv.setImageResource(R.drawable.home_slide_stock_kb)
            eight_tv.text = "KB"
            nine_iv.setImageResource(R.drawable.home_slide_stock_seoul_auction)
            nine_tv.text = "서울옥션"
            ten_iv.setImageResource(R.drawable.home_slide_stock_google)
            ten_tv.text = "구글"
        }
    }
}
