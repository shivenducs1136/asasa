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
import kotlinx.android.synthetic.main.horizontalcollectionview.view.*

class FoodAdapter(private val context: Context): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private var collect:List<food.Collection?> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.horizontalcollectionview, parent, false)
        return FoodViewHolder(v)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val collect= collect[position]?.collection
        holder.restname.text= collect?.title.toString()


        Picasso.with(context)
            .load(collect?.imageUrl)
            .into(holder.foodimage)

        holder.itemView.setOnClickListener {

          /*  val intent = Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse(collect?.url.toString())
            ContextCompat.startActivity(holder.itemView.context, intent, null)*/

            var bundle=Bundle()
            bundle.putString("url",collect?.url)
            it.findNavController().navigate(R.id.action_homeFragment_to_discoverRestrauntFragment,bundle)

        }
    }

    override fun getItemCount(): Int {
        return collect.size
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodimage=itemView.collection_img
        val restname=itemView.collection_txt

    }

    fun setStateWiseTracker(list: List<food.Collection?>){
        this.collect=list
        notifyDataSetChanged()
    }
}
