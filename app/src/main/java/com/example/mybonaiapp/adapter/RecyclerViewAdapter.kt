package com.example.mybonaiapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybonaiapp.R
import com.example.mybonaiapp.model.Content
import com.example.mybonaiapp.model.ContentX
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.squareup.picasso.Picasso


class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    companion object{
        const val TAG ="RecyclerViewAdapter"
    }
    var items = listOf<Content>()

    fun setUpData(items : List<Content>){
        this.items =items
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val imageBrochure =view.findViewById<ImageView>(R.id.imageBrochure)
        val tvRetailerName =view.findViewById<TextView>(R.id.tvRetailerName)
        fun bind(data :Content){
            try {
                if (data.content is LinkedTreeMap<*, *>) {
                    val myClass = toObject(data.content)
                    if (myClass?.type?.toString() != null) {
                        when (myClass?.type?.lowercase()) {
                            "brochure" -> {
                                tvRetailerName.setText(myClass.retailer.name )
                                val imageUrl = myClass.brochureImage
                                Picasso.get().load(imageUrl).into(imageBrochure)
                            }
                            "brochurePremium" -> {
                                tvRetailerName.setText(myClass.retailer.name ?: "Lidl")
                                val imageUrl = myClass.brochureImage
                                Picasso.get().load(imageUrl).into(imageBrochure)
                            }
                        }
                    }
                }
            }catch (e:Exception){

            }

        }

        fun  toObject(jsonStr:Object): ContentX? {
            if (jsonStr == null) return null
            val gson = Gson()
            try {
                return gson.fromJson(gson.toJsonTree(jsonStr),ContentX::class.java)

            } catch (e:Exception){
                return null
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.recyler_list_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun findClosedDistance(number :Int){
        val distance :Int = Math.abs( number - 5)
    }



}