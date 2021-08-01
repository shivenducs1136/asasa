package com.example.zomatoapp.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.api.CollectionX
import com.example.zomatoapp.api.food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item.view.*

class SearchedCityAdapter(private val context: Context): RecyclerView.Adapter<SearchedCityAdapter.SearchedCityViewHolder>() {
    private var sercollect:List<food.Collection?> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedCityViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return SearchedCityViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchedCityViewHolder, position: Int) {

        val collect= sercollect[position]?.collection

            holder.restname.text= collect?.title.toString()
            holder.fooddesc.text= collect?.description.toString()

            Picasso.with(context)
                .load(collect?.imageUrl)
                .into(holder.foodimage)

            holder.itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data= Uri.parse(collect?.url.toString())
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        holder.restname.text= collect?.title.toString()
        holder.fooddesc.text= collect?.description.toString()

        Picasso.with(context)
            .load(collect?.imageUrl)
            .into(holder.foodimage)

        holder.itemView.setOnClickListener {

            var bundleM= Bundle()
            bundleM.putString("urlM",collect?.url)
            it.findNavController().navigate(R.id.mapRestrauntFragment,bundleM)
        }
    }

    override fun getItemCount(): Int {
        return sercollect.size
    }

    class SearchedCityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodimage=itemView.iv_image
        val restname=itemView.iv_tittle
        val fooddesc=itemView.iv_cusines

    }

    fun searchedsetStateWiseTracker(list: List<food.Collection?>){
        this.sercollect=list
        notifyDataSetChanged()
    }
}
