package app.woovictory.liiv_live.view.quiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.SnapHelper
import android.util.Log
import android.view.View
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.QuizReviewAdapter
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.model.GetQuizReviewResponse
import app.woovictory.liiv_live.model.QuizData
import kotlinx.android.synthetic.main.activity_quiz_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizReviewActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            quiz_review_back_btn -> finish()
        }
    }

    lateinit var reviewAdapterData1 : ArrayList<QuizData>
    lateinit var reviewAdapterData2 : ArrayList<QuizData>
    lateinit var reviewAdapterData3 : ArrayList<QuizData>

//    lateinit var quizReviewAdapter: QuizReviewAdapter
//    val item_list: ArrayList<QuizReviewData> by lazy {
//        ArrayList<QuizReviewData>()
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_review)

        reviewAdapterData1 = ArrayList()
        reviewAdapterData2 = ArrayList()
        reviewAdapterData3 = ArrayList()
        quiz_review_back_btn.setOnClickListener(this)

//        for (i in 0..5) {
//
//            item_list.add(QuizReviewData("Q. 주식 거래 정규 마감 시간은?", 0))
//            item_list.add(QuizReviewData("Q. 한 번 지급하고 나면 회수할 수 없는 비용을 뜻하는 용어는?", 1))
//        }


/*        var snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(quizReviewRvOne)*/

        requestQuizReview(SharedPreferenceController.getMyId(applicationContext))
//        quizReviewRvOne.setHasFixedSize(true)
//        quizReviewAdapter = QuizReviewAdapter(item_list, this)
//
//        quizReviewRvOne.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        quizReviewRvOne.adapter = quizReviewAdapter
//
//        snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(quizReviewRvTwo)
//        quizReviewRvTwo.setHasFixedSize(true)
//
//        quizReviewRvTwo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        quizReviewRvTwo.adapter = quizReviewAdapter
//
//        snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(quizReviewRvThree)
//        quizReviewRvThree.setHasFixedSize(true)
//
//        quizReviewRvThree.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        quizReviewRvThree.adapter = quizReviewAdapter

    }

    fun requestQuizReview(userID: String){
        var networkService : NetworkService = ApplicationController.instance.networkService
        var getQuizReview = networkService.getQuizReview(userID)
        getQuizReview.enqueue(object: Callback<GetQuizReviewResponse> {
            override fun onFailure(call: Call<GetQuizReviewResponse>?, t: Throwable?) {
                Log.v("TAG", "퀴즈 리뷰 가져오기 실패")
            }

            override fun onResponse(call: Call<GetQuizReviewResponse>?, response: Response<GetQuizReviewResponse>?) {
                if (response!!.isSuccessful){

                    // 날짜
                    quizReviewTextFirst.text = response!!.body()!!.data[0].date

                    quizReviewTextSecond.text = response!!.body()!!.data[1].date

                    quizReviewTextThird.text = response!!.body()!!.data[2].date









                    reviewAdapterData1 = response.body()!!.data[0].quizData
                    var snapHelper: SnapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(quizReviewRvOne)
                    quizReviewRvOne.setHasFixedSize(true)
                    var quizReviewAdapter1 = QuizReviewAdapter(reviewAdapterData1, applicationContext)
                    quizReviewRvOne.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                    quizReviewRvOne.adapter = quizReviewAdapter1

                    reviewAdapterData2 = response.body()!!.data[1].quizData
                    quizReviewRvOne.setHasFixedSize(true)
                    snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(quizReviewRvTwo)
                    var quizReviewAdapter2 = QuizReviewAdapter(reviewAdapterData2, applicationContext)
                    quizReviewRvTwo.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                    quizReviewRvTwo.adapter = quizReviewAdapter2

                    reviewAdapterData3 = response.body()!!.data[2].quizData
                    quizReviewRvOne.setHasFixedSize(true)
                    snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(quizReviewRvThree)
                    var quizReviewAdapter3 = QuizReviewAdapter(reviewAdapterData3, applicationContext)
                    quizReviewRvThree.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                    quizReviewRvThree.adapter = quizReviewAdapter3
                }

//
//                var quizReviewAdapter = QuizReviewAdapter()
//                quizReviewRvOne.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//                quizReviewRvOne.adapter = quizReviewAdapter
            }
        })

    }


}
