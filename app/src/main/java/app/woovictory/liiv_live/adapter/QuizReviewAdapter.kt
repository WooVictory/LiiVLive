package app.woovictory.liiv_live.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.model.QuizReviewData
import kotlinx.android.synthetic.main.item_quiz_review.view.*

/**
 * Created by VictoryWoo
 */
class QuizReviewAdapter(var items : ArrayList<QuizReviewData>, var context : Context)
    : RecyclerView.Adapter<QuizReviewAdapter.QuizReviewViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_review, parent, false)
        return QuizReviewViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: QuizReviewViewHolder, position: Int) {
        holder.quiz_title.text = items[position].quizTitle
    }

    inner class QuizReviewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var quiz_title : TextView = itemView.findViewById(R.id.quiz_title_item)
    }
}