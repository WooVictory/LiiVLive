package app.woovictory.liiv_live.view.quiz

import android.graphics.PorterDuff
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import app.woovictory.liiv_live.Get.GetQuizAnswerResponse
import app.woovictory.liiv_live.Get.GetQuizData
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.SharedPreferenceController
import app.woovictory.liiv_live.db.pointItemDataList
import app.woovictory.liiv_live.view.popup.LiveFinishPopUpActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_quiz.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            live_quiz_answer_one->{
                if (!live_quiz_answer_one.isSelected){
                    live_quiz_answer_one.isSelected = true
                    if(live_quiz_answer_one.isSelected){


                        // 퀴즈의 id가 담김
                        quiz_id = data.question.id.toString()
                        select_answer = data.question_example[0].id.toString()
                        quiz_title = data.question.question

                        var task = QuizTask()
                        task.execute(4)
                        //postQuiz(answer)
                    }

                    live_quiz_answer_one.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),
                        PorterDuff.Mode.SRC_IN)
                    live_quiz_answer_two.background = getDrawable(R.drawable.border_quiz_answer)
                    live_quiz_answer_three.background = getDrawable(R.drawable.border_quiz_answer)
                } else{
                    live_quiz_answer_one.isSelected = false
                    live_quiz_answer_one.background = getDrawable(R.drawable.border_quiz_answer)
                }
            }
            live_quiz_answer_two->{
                if(!live_quiz_answer_two.isSelected){
                    live_quiz_answer_two.isSelected = true
                    if(live_quiz_answer_two.isSelected){
                        quiz_id = data.question.id.toString()
                        select_answer = data.question_example[1].id.toString()
                        quiz_title = data.question.question

                        var task = QuizTask()
                        task.execute(4)

                    }
                    live_quiz_answer_two.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),
                        PorterDuff.Mode.SRC_IN)
                    live_quiz_answer_one.background = getDrawable(R.drawable.border_quiz_answer)
                    live_quiz_answer_three.background = getDrawable(R.drawable.border_quiz_answer)
                }else{
                    live_quiz_answer_two.isSelected = false
                    live_quiz_answer_two.background = getDrawable(R.drawable.border_quiz_answer)
                }

            }
            live_quiz_answer_three->{
                if(!live_quiz_answer_three.isSelected){
                    live_quiz_answer_three.isSelected = true
                    if(live_quiz_answer_three.isSelected){
                        quiz_id = data.question.id.toString()
                        select_answer = data.question_example[2].id.toString()
                        quiz_title = data.question.question

                        var task = QuizTask()
                        task.execute(4)

                    }
                    live_quiz_answer_three.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),
                        PorterDuff.Mode.SRC_IN)
                    live_quiz_answer_one.background = getDrawable(R.drawable.border_quiz_answer)
                    live_quiz_answer_two.background = getDrawable(R.drawable.border_quiz_answer)
                }else{
                    live_quiz_answer_three.isSelected = false
                    live_quiz_answer_three.background = getDrawable(R.drawable.border_quiz_answer)
                }
            }

        }
    }

    fun init(){
        live_quiz_answer_one.setOnClickListener(this)
        live_quiz_answer_two.setOnClickListener(this)
        live_quiz_answer_three.setOnClickListener(this)
        networkService = ApplicationController.instance.networkService
    }

    lateinit var networkService: NetworkService
    lateinit var quiz_id : String
    lateinit var select_answer : String
    lateinit var quiz_title : String
    lateinit var data : GetQuizData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        quiz_text
        init()

        var s = intent.getStringExtra("json")
        Log.v("TAG", s)

//        var obj  = JSONObject(s)
//        var a: ArrayList<GetQuizData> = ArrayList()
//        var gson: Gson = Gson()
//
//        val json = """ +
//   { "title": "Most elegant way of using Gson + Kotlin with default values and null safety",
//     "body": null,
//     "viewCount": 9999,
//     "payWall": false,
//     "ignoredProperty": "Ignored"
//   }
//"""
        data = Gson().fromJson(s, GetQuizData::class.java)

        Log.v("TAG", "이 밑")
        Log.v("TAG", data.question.question)

        //
        quiz_title_item.text = data.question.question
        quiz_answer_one_tv.text = data.question_example[0].content
        quiz_answer_two_tv.text = data.question_example[1].content
        quiz_answer_three_tv.text = data.question_example[2].content



        //circle_layout.bringToFront()


    }

    inner class QuizTask() : AsyncTask<Int,Int,String>(){
        override fun doInBackground(vararg params: Int?): String {
            var num = params[0]!!

            while(num > 1){
                num -=1
                publishProgress(num)
                Thread.sleep(1000)
            }
            return "완료"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            quiz_text.text = values[0].toString()
            //finish()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            quiz_text.text = "1"
            //postQuiz(answer)
            finish()
            postQuiz(quiz_id,select_answer)
//            var intent : Intent = Intent(applicationContext, QuizAnswerActivity::class.java)
//
//            intent.putExtra("quiz_id",quiz_id)
//            intent.putExtra("select_answer",select_answer)
//            intent.putExtra("quiz_title",quiz_title)
          /*  if(pointItemDataList.count == 3){
                toast("하루 3문제를 푸셨습니다. 내일 풀어주세요.")
                pointItemDataList.count = 0
            }else{

            }*/
//            startActivity(intent)



        }

        override fun onPreExecute() {
            super.onPreExecute()
            circle_layout.visibility = View.GONE
            //circle_layout2.background = getDrawable(R.drawable.border_circle_red)
            circle_layout2.visibility = View.VISIBLE
            quiz_text.setTextColor(ContextCompat.getColor(applicationContext,R.color.redColor))
            circle_layout2.background = getDrawable(R.drawable.border_circle_red)
        }
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
                    /*var arr = response.body()!!.data
                    for (i in 0..arr.size - 1) {
                        if (arr[i].answer_flag == 1) {
                            when (i) {
                                0 -> {
                                    quiz_answer_one_layout.background.setColorFilter(
                                        ContextCompat.getColor(this@QuizActivity, R.color.mainColor),
                                        PorterDuff.Mode.SRC_IN
                                    )
                                }
                                1 -> {
                                    quiz_answer_two_layout.background.setColorFilter(
                                        ContextCompat.getColor(this@QuizActivity, R.color.mainColor),
                                        PorterDuff.Mode.SRC_IN
                                    )
                                }
                                2 -> {
                                    quiz_answer_three_layout.background.setColorFilter(
                                        ContextCompat.getColor(this@QuizActivity, R.color.mainColor),
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
                        Glide.with(this@QuizActivity).load(R.drawable.live_x_icon).into(quiz_answer_image)
                    else
                        Glide.with(this@QuizActivity).load(R.drawable.live_o_icon).into(quiz_answer_image)

                    quiz_answer_text_view_one.text = arr[0].content
                    quiz_answer_people_text_one.text = arr[0].select_people.toString()

                    quiz_answer_text_view_two.text = arr[1].content
                    quiz_answer_people_text_two.text = arr[1].select_people.toString()

                    quiz_answer_text_view_three.text = arr[2].content
                    quiz_answer_people_text_three.text = arr[2].select_people.toString()
*/


/*
                    Log.v("woo 854 success: ", response!!.body()!!.msg)
                    Log.v("woo 854 success: ", response!!.body()!!.toString())
                    Log.v("woo 854 people : ", total.toString())*/

             /*       pointItemDataList.count += 1
                    Log.v("woo 854 count : ", pointItemDataList.count.toString())
                    var handler = Handler()
                    handler.postDelayed({
                        finish()
                        if (pointItemDataList.count == 3) {
                            startActivity<LiveFinishPopUpActivity>()
                        }
                    }, 3000)
*/

                }
            }

        })

    }

}
