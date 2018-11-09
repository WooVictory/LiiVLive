package app.woovictory.liiv_live.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import app.woovictory.liiv_live.MainActivity
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.view.home.HomeActivity
import org.jetbrains.anko.layoutInflater

class NavigationAdapter(
    homeActivity: HomeActivity,
    internal var result: ArrayList<String>,
    internal var imageId: ArrayList<Int>
) : BaseAdapter() {
    internal var context: Context

    init {
        context = homeActivity
        inflater = context.layoutInflater

    }

    override fun getCount(): Int {
        return result.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class Holder {
        internal var tv: TextView? = null
        internal var img: ImageView? = null
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val holder = Holder()
        val rowView: View
        rowView = inflater!!.inflate(R.layout.item_navigation_menu, parent, false)
        holder.tv = rowView.findViewById<View>(R.id.tvlstName) as TextView
        holder.img = rowView.findViewById<View>(R.id.img) as ImageView
        holder.tv!!.text = result[position]
        holder.img!!.setImageResource(imageId[position])
        rowView.setOnClickListener {
            Toast.makeText(context, "you Clicked " + result[position], Toast.LENGTH_LONG).show()
        }
        return rowView
    }

    companion object {
        private var inflater: LayoutInflater? = null
    }

}