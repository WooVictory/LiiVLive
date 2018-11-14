package app.woovictory.liiv_live.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.woovictory.liiv_live.R
import app.woovictory.liiv_live.model.CoffeeData
import org.w3c.dom.Text

/**
 * Created by VictoryWoo
 */
class CoffeeAdapter(var items : ArrayList<CoffeeData>, var context : Context)
    : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_coupon_coffee, parent, false)
        return CoffeeViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.coffeeImage.setImageResource(items[position].coffee_img)
        holder.coffeeBrand.text = items[position].coffee_brand
        holder.coffeeType.text = items[position].coffee_type
        holder.coffeePrice.text = items[position].coffee_price
    }

    inner class CoffeeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var coffeeImage : ImageView = itemView.findViewById(R.id.coffee_image_item)
        var coffeeBrand : TextView = itemView.findViewById(R.id.coffee_brand_name)
        var coffeeType : TextView = itemView.findViewById(R.id.coffee_type_item)
        var coffeePrice : TextView = itemView.findViewById(R.id.coffee_price_item)

    }

}