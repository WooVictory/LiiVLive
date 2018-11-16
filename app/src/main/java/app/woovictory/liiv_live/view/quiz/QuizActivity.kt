package app.woovictory.liiv_live.view.quiz

import android.graphics.PorterDuff
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import app.woovictory.liiv_live.Get.GetQuizData
import app.woovictory.liiv_live.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            live_quiz_answer_one->{
                if (!live_quiz_answer_one.isSelected){
                    live_quiz_answer_one.isSelected = true
                    var handler = Handler()
                    if(live_quiz_answer_one.isSelected){
                      /*  handler.postDelayed({
                            circle_layout.visibility = View.VISIBLE
                            circle_layout2.visibility = View.GONE
                            var task = QuizTasK()
                            task.execute(3)
                        },3000)*/
                        var task = QuizTasK()
                        task.execute(4)
                    }

                    live_quiz_answer_one.background.setColorFilter(ContextCompat.getColor(this, R.color.mainColor),
                        PorterDuff.Mode.SRC_IN)
                } else{
                    live_quiz_answer_one.isSelected = false
                    live_quiz_answer_one.background = getDrawable(R.drawable.border_quiz_answer)
                }
            }
            live_quiz_answer_two->{

            }
            live_quiz_answer_three->{

            }

        }
    }

    fun init(){
        live_quiz_answer_one.setOnClickListener(this)
        live_quiz_answer_two.setOnClickListener(this)
        live_quiz_answer_three.setOnClickListener(this)
    }

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
        val data = Gson().fromJson(s, GetQuizData::class.java)

        Log.v("TAG", "이 밑")
        Log.v("TAG", data.question.question)



        //circle_layout.bringToFront()


    }

    inner class QuizTasK : AsyncTask<Int,Int,String>(){
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

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            quiz_text.text = "1"


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
}
