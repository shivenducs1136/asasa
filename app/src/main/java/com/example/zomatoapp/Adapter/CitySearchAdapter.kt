package com.example.zomatoapp.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.cityapi.city
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.citysearchlistlayout.view.*

class CitySearchAdapter (private val context: Context): RecyclerView.Adapter<CitySearchAdapter.CityViewHolder>() {
    private var collectid:List<city.LocationSuggestion?> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.citysearchlistlayout, parent, false)
        return CityViewHolder(v)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val collectid= collectid[position]
        if(collectid?.country_id == 1){

            holder?.restname.text = collectid?.name.toString()
            Picasso.with(context)
                .load(collectid?.country_flag_url)
                .into(holder.flag)
            holder.itemView.setOnClickListener {

                var bundle = Bundle()
                bundle.putString("id1",collectid?.id.toString())
                it.findNavController().navigate(R.id.action_mapFragment2_to_serachedCityResultsFragment,bundle)
            }
        }

            }





    override fun getItemCount(): Int {
        return collectid.size
    }

    fun citysetStateWiseTracker(list: List<city.LocationSuggestion?>){
        this.collectid=list
        notifyDataSetChanged()
    }


    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val restname=itemView.citynametxt
        val flag  = itemView.flagimg
    }



}
