package app.woovictory.liiv_live.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import app.woovictory.liiv_live.Get.GetQuizAnswerReviewResponse
import app.woovictory.liiv_live.Network.ApplicationController
import app.woovictory.liiv_live.Network.NetworkService
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.model.QuizData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class QuizReviewAdapter(var items: ArrayList<QuizData>, var context: Context) :
    RecyclerView.Adapter<QuizReviewAdapter.QuizReviewViewHolder>() {

    var vr = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_review, parent, false)
        return QuizReviewViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuizReviewViewHolder, position: Int) {

        setOnClickListener(holder)
        // 틀림
        if (items[position].isRight == 0){
            // 틀림버튼
            holder.quiz_icon.setImageResource(R.drawable.quiz_review_x_btn)
            if(items[position].AnswerID == items[position].example[0].id){
                holder.quiz_answer_one.background.setColorFilter(ContextCompat.getColor(context, R.color.greyblue),PorterDuff.Mode.SRC_IN)
            }else if(items[position].AnswerID == items[position].example[1].id){
                holder.quiz_answer_two.background.setColorFilter(ContextCompat.getColor(context, R.color.greyblue),PorterDuff.Mode.SRC_IN)
            }else if(items[position].AnswerID == items[position].example[2].id){
                holder.quiz_answer_three.background.setColorFilter(ContextCompat.getColor(context, R.color.greyblue),PorterDuff.Mode.SRC_IN)
            }

        }else{


            if(items[position].example[0].answer_flag == 1){
                holder.quiz_answer_one.background.setColorFilter(ContextCompat.getColor(context, R.color.mainColor),PorterDuff.Mode.SRC_IN)
            }else if(items[position].example[1].answer_flag == 1){
                holder.quiz_answer_two.background.setColorFilter(ContextCompat.getColor(context, R.color.mainColor),PorterDuff.Mode.SRC_IN)
            }else if(items[position].example[2].answer_flag == 1){
                holder.quiz_answer_three.background.setColorFilter(ContextCompat.getColor(context, R.color.mainColor),PorterDuff.Mode.SRC_IN)
            }
            holder.quiz_icon.setImageResource(R.drawable.quiz_review_o_btn)

        }

        // 보기 1
        holder.quiz_answer_one_text.text = items[position].example[0].content

        // 보기 2
        holder.quiz_answer_two_text.text = items[position].example[1].content

        // 보기 3
        holder.quiz_answer_three_text.text = items[position].example[2].content
        // 퀴즈 타이블
        holder.quiz_title.text = items[position].question





        requestQuizAnswer(items[position].quizId, holder)

//
//        // 틀린 경우
//        if (items[position].checkFlag == 0){
//            holder.quiz_icon.setImageResource(R.drawable.quiz_review_x_btn)
//            holder.quiz_answer_one.background.setColorFilter(ContextCompat.getColor(context, R.color.greyblue),PorterDuff.Mode.SRC_IN)
//        }
//        // 맞은 경우
//        else{
//            holder.quiz_icon.setImageResource(R.drawable.quiz_review_o_btn)
//            holder.quiz_answer_one.background.setColorFilter(ContextCompat.getColor(context, R.color.mainColor),PorterDuff.Mode.SRC_IN)
//        }
//
//        holder.quiz_explain_button.setOnClickListener {
//            if(!holder.quiz_explain_button.isSelected){
//                holder.quiz_explain_button.isSelected=true
//                holder.explain_before_layout.visibility = View.GONE
//                holder.explain_after_layout.visibility = View.VISIBLE
//
//            }else{
//                holder.quiz_explain_button.isSelected=false
//                holder.explain_before_layout.visibility = View.VISIBLE
//                holder.explain_after_layout.visibility = View.GONE
//            }
//        }

      /*  var animOf1 : Animation = AnimationUtils.loadAnimation(context, R.anim.quiz_review_progress_bar_anim_1)
        var animOf2 : Animation = AnimationUtils.loadAnimation(context, R.anim.quiz_review_progress_bar_anim_2)

        animOf1.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                holder.quiz_answer_one_text.visibility = View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {
                holder.quiz_answer_one_text.visibility = View.INVISIBLE
                holder.quiz_answer_one.background.setColorFilter(ContextCompat.getColor(context, R.color.mainColor),PorterDuff.Mode.SRC_IN)
                holder.quiz_answer_one.visibility = View.VISIBLE

            }

        }
        )
        holder.quiz_answer_one.startAnimation(animOf1)*/

        //holder.quiz_answer_one.background.setColorFilter(ContextCompat.getColor(context, R.color.mainColor),PorterDuff.Mode.SRC_IN)



    }

    private fun setOnClickListener(holder: QuizReviewViewHolder){
        holder.quiz_explain_button.setOnClickListener {
            if(vr == 0){
                holder.explain_after_layout.visibility = View.VISIBLE
                holder.explain_before_layout.visibility = View.GONE
                holder.quiz_explain_button.setImageResource(R.drawable.quiz_review_commentary_btn_blue)
                vr = 1
            }else{
                holder.explain_after_layout.visibility = View.GONE
                holder.explain_before_layout.visibility = View.VISIBLE
                holder.quiz_explain_button.setImageResource(R.drawable.quiz_review_commentary_btn_gray)
                vr = 0
            }

        }
    }

    fun requestQuizAnswer(quizID : Int, holder: QuizReviewViewHolder){

        var networkService : NetworkService = ApplicationController.instance.networkService

        var getQuizAnswerReview = networkService.getQuizAnswerReview(quizID)

        getQuizAnswerReview.enqueue(object: Callback<GetQuizAnswerReviewResponse> {
            override fun onFailure(call: Call<GetQuizAnswerReviewResponse>?, t: Throwable?) {
                Log.v("TAG", "문제 리뷰 해답 불러오기 실패")
            }

            override fun onResponse(call: Call<GetQuizAnswerReviewResponse>?, response: Response<GetQuizAnswerReviewResponse>?) {
                if(response!!.isSuccessful) {
                    holder.quiz_answer_text.text = response.body()!!.data[0].content
                    holder.quiz_answer_content_text.text = response.body()!!.data[0].answer_content
                }
            }

        })
    }

    inner class QuizReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 퀴즈 문제
        var quiz_title: TextView = itemView.findViewById(R.id.quiz_title_item)
        // 맞, 틀 O,X
        var quiz_icon: ImageView = itemView.findViewById(R.id.quiz_check_btn)
        // 해답 버튼
        var quiz_explain_button: ImageView = itemView.findViewById(R.id.explain_btn)

        // 보기 1 박스
        var quiz_answer_one: RelativeLayout = itemView.findViewById(R.id.quiz_answer_one)
        // 보기 1 TexView
        var quiz_answer_one_text : TextView = itemView.findViewById(R.id.quiz_answer_one_tv)

        var quiz_answer_two: RelativeLayout = itemView.findViewById(R.id.quiz_answer_two)
        var quiz_answer_two_text : TextView = itemView.findViewById(R.id.quiz_answer_two_tv)
        var quiz_answer_three: RelativeLayout = itemView.findViewById(R.id.quiz_answer_three)
        var quiz_answer_three_text : TextView = itemView.findViewById(R.id.quiz_answer_three_tv)

        // 해답이 아닐 때의 Reivew Itme 뷰
        var explain_before_layout : RelativeLayout = itemView.findViewById(R.id.quiz_explain_before_layout)
        // 해답일 때의 Reivew Itme 뷰
        var explain_after_layout : RelativeLayout = itemView.findViewById(R.id.quiz_explain_after_layout)

        // 해답 화면 보기 답
        var quiz_answer_text : TextView = itemView.findViewById(R.id.quiz_answer_text)

        // 해답 화면 풀이
        var quiz_answer_content_text : TextView = itemView.findViewById(R.id.quiz_answer_content_text)

    }
}