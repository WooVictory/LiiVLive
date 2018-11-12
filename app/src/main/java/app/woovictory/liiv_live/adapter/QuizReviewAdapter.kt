package app.woovictory.liiv_live.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.model.QuizReviewData
import app.woovictory.liiv_live.util.CustomProgress
import kotlinx.android.synthetic.main.item_quiz_review.view.*

/**
 * Created by VictoryWoo
 */
class QuizReviewAdapter(var items: ArrayList<QuizReviewData>, var context: Context) :
    RecyclerView.Adapter<QuizReviewAdapter.QuizReviewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_review, parent, false)
        return QuizReviewViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuizReviewViewHolder, position: Int) {
        holder.quiz_title.text = items[position].quizTitle

        // 틀린 경우
        if (items[position].checkFlag == 0)
            holder.quiz_icon.setImageResource(R.drawable.quiz_review_x_btn)
        // 맞은 경우
        else
            holder.quiz_icon.setImageResource(R.drawable.quiz_review_o_btn)


        var animOf1 : Animation = AnimationUtils.loadAnimation(context, R.anim.quiz_review_progress_bar_anim_1)
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
        holder.quiz_answer_one.startAnimation(animOf1)



    }

    inner class QuizReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var quiz_title: TextView = itemView.findViewById(R.id.quiz_title_item)
        var quiz_icon: ImageView = itemView.findViewById(R.id.quiz_check_btn)
        var quiz_explain_button: ImageView = itemView.findViewById(R.id.explain_btn)
        var quiz_answer_one: RelativeLayout = itemView.findViewById(R.id.quiz_answer_one)
        var quiz_answer_one_text : TextView = itemView.findViewById(R.id.quiz_answer_one_tv)
    }
}