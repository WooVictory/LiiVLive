package app.woovictory.liiv_live.view.quiz

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.Log
import app.woovictory.liiv_live.Get.GetQuizAnswerResponse
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.Post.PostRefreshFcmTokenResponse
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.db.pointItemDataList
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz_answer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizAnswerActivity : AppCompatActivity() {

    lateinit var networkService: NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_answer)
        networkService = ApplicationController.instance.networkService

        getData()
    }

    fun getData() {
        var quiz_id = intent.getStringExtra("quiz_id")
        var select_answer = intent.getStringExtra("select_answer")
        var quiz_title = intent.getStringExtra("quiz_title")
        Log.v("woo 854", quiz_id)
        Log.v("woo 854", select_answer)
        Log.v("woo 854", quiz_title)

        quiz_answer_title.text = quiz_title

        postQuiz(quiz_id, select_answer)
    }

    fun postQuiz(quizId: String, selectAnswer: String) {
        var quiz_check_answer = selectAnswer
        var quizResponse = networkService.postQuizResponse(
            quizId, quiz_check_answer
            , SharedPreferenceController.getMyId(this)
        )
        quizResponse.enqueue(object : Callback<GetQuizAnswerResponse> {
            override fun onFailure(call: Call<GetQuizAnswerResponse>, t: Throwable) {
                Log.v("woo 603 fail : ", t.toString())
            }

            override fun onResponse(
                call: Call<GetQuizAnswerResponse>,
                response: Response<GetQuizAnswerResponse>
            ) {
                if (response!!.isSuccessful) {
                    var arr = response.body()!!.data
                    for (i in 0..arr.size - 1) {
                        if (arr[i].answer_flag == 1) {
                            when (i) {
                                0 -> {
                                    quiz_answer_one_layout.background.setColorFilter(
                                        ContextCompat.getColor(this@QuizAnswerActivity, R.color.mainColor),
                                        PorterDuff.Mode.SRC_IN
                                    )
                                }
                                1 -> {
                                    quiz_answer_two_layout.background.setColorFilter(
                                        ContextCompat.getColor(this@QuizAnswerActivity, R.color.mainColor),
                                        PorterDuff.Mode.SRC_IN
                                    )
                                }
                                2 -> {
                                    quiz_answer_three_layout.background.setColorFilter(
                                        ContextCompat.getColor(this@QuizAnswerActivity, R.color.mainColor),
                                        PorterDuff.Mode.SRC_IN
                                    )
                                }
                            }
                        }
                    }
                    var total: Int = 0

                    for (i in 0..arr.size - 1) {
                        total += arr[i].select_people
                    }
                    if (response.body()!!.msg == "퀴즈 틀림")
                        Glide.with(this@QuizAnswerActivity).load(R.drawable.quiz_review_x_btn).into(quiz_answer_image)
                    else
                        Glide.with(this@QuizAnswerActivity).load(R.drawable.quiz_review_o_btn).into(quiz_answer_image)

                    quiz_answer_text_view_one.text = arr[0].content
                    quiz_answer_people_text_one.text = arr[0].select_people.toString()

                    quiz_answer_text_view_two.text = arr[1].content
                    quiz_answer_people_text_two.text = arr[1].select_people.toString()

                    quiz_answer_text_view_three.text = arr[2].content
                    quiz_answer_people_text_three.text = arr[2].select_people.toString()




                    Log.v("woo 854 success: ", response!!.body()!!.msg)
                    Log.v("woo 854 success: ", response!!.body()!!.toString())
                    Log.v("woo 854 people : ", total.toString())

                    pointItemDataList.count += 1
                    Log.v("woo 854 count : ", pointItemDataList.count.toString())
                    var handler = Handler()
                    handler.postDelayed({
                        finish()
                    },3000)
                }
            }

        })
    }
}
