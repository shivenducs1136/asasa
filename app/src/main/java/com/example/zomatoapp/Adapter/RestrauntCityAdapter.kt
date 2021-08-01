package com.example.zomatoapp.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.RestrauntApi.Restaurant
import com.example.zomatoapp.RestrauntApi.RestrauntApi
import com.example.zomatoapp.api.food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.horizontalcollectionview.view.*

class RestrauntCityAdapter (private val context: Context): RecyclerView.Adapter<RestrauntCityAdapter.RestrauntViewHolder>() {
    private var collect:List<Restaurant> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestrauntViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return RestrauntViewHolder(v)
    }

    override fun onBindViewHolder(holder: RestrauntViewHolder, position: Int) {

        val collectionitem= collect[position].restaurant


            holder.restname?.text= collectionitem?.name
            holder.foodcusines?.text= collectionitem?.cuisines
            val varprice = collectionitem?.price_range
            var actual_price : Int
            if(varprice == 1){
                actual_price = 100
            }
            else{
                actual_price = 100 + (varprice!! - 1) * 50
            }
            holder.foodpricing?.text = "Rs. " + actual_price.toString() + " for one"
            holder.foodrating?.text = collectionitem?.user_rating!!.aggregate_rating




            if(collectionitem?.featured_image!= ""){
                Picasso.with(context)
                    .load(collectionitem.featured_image)
                    .placeholder(R.drawable.loadinggif)
                    .into(holder.foodimage)
            }
        holder.itemView.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("Rurl",collectionitem.menu_url.toString())
            it.findNavController().navigate(R.id.action_homeFragment_to_webFragment,bundle)
        }


      //  var bundle = Bundle()
     //   bundle.putString("CityName",collectionitem.location!!.city.toString())



    }

    override fun getItemCount(): Int {
        return collect.size
    }

    class RestrauntViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodimage=itemView.iv_image
        val restname=itemView.iv_tittle
        val foodcusines=itemView.iv_cusines
        val foodrating = itemView.iv_rating
        val foodpricing = itemView.iv_price


    }

    fun setStateRestrauntWiseTracker(list: List<Restaurant>){
        this.collect=list
        notifyDataSetChanged()
    }

}
