package app.woovictory.liiv_live.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.db.PointreeHistoryData

/**
 * Created by VictoryWoo
 */
class PointreeHistoryAdapter(var items: ArrayList<PointreeHistoryData>, var context: Context) :
    RecyclerView.Adapter<PointreeHistoryAdapter.PointreeHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointreeHistoryViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_pointree_history, parent, false)
        return PointreeHistoryViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PointreeHistoryViewHolder, position: Int) {

        holder.historyMethod.text = items[position].history_method
        holder.historyPoint.text = items[position].history_point.toString() + " P"
        holder.historyDate.text = items[position].history_date
        holder.historyType.text = items[position].history_type

        if (items[position].history_method.equals("획득")) {
            holder.historyMethod.setTextColor(ContextCompat.getColor(context, R.color.greyblue))
            holder.historyPoint.setTextColor(ContextCompat.getColor(context, R.color.greyblue))
        } else if(items[position].history_method.equals("차감")){
            holder.historyMethod.setTextColor(ContextCompat.getColor(context, R.color.mainColor))
            holder.historyPoint.setTextColor(ContextCompat.getColor(context, R.color.mainColor))
        }

    }

    inner class PointreeHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var historyDate: TextView = itemView.findViewById(R.id.pointreeHistoryDateItem)
        var historyType: TextView = itemView.findViewById(R.id.pointreeHistoryTypeItem)
        var historyPoint: TextView = itemView.findViewById(R.id.pointreeHistoryPointItem)
        var historyMethod: TextView = itemView.findViewById(R.id.pointreeHistoryMethodItem)
    }
}