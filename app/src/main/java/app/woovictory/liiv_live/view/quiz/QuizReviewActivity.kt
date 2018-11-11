package app.woovictory.liiv_live.view.quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.SnapHelper
import android.view.View
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.adapter.QuizReviewAdapter
import app.woovictory.liiv_live.model.QuizReviewData
import kotlinx.android.synthetic.main.activity_quiz_review.*
import kotlinx.android.synthetic.main.item_quiz_review.*

class QuizReviewActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            quiz_review_back_btn -> finish()
        }
    }

    lateinit var quizReviewAdapter: QuizReviewAdapter
    val item_list: ArrayList<QuizReviewData> by lazy {
        ArrayList<QuizReviewData>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_review)

        quiz_review_back_btn.setOnClickListener(this)

        for (i in 0..10)
            item_list.add(QuizReviewData("Q. 주식 거래 정규 마감 시간은?"))


        var snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(quizReviewRv)
        quizReviewRv.setHasFixedSize(true)
        quizReviewAdapter = QuizReviewAdapter(item_list, this)

        quizReviewRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        quizReviewRv.adapter = quizReviewAdapter


    }
}
