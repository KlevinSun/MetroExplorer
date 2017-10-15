package com.example.sunka.metroexplorer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.row_stations.view.*



/**
 * Created by sunka on 2017/9/29.
 */

// recycler View adapter
class MetroStationListAdapter(private var context: Context, private var MetroData: ArrayList<String>, private var ZipData: ArrayList<String>) : RecyclerView.Adapter<MetroStationListAdapter.ViewHolder>(){

    private var TAG: String = "MetroStationAdapter"



    override fun getItemCount() = MetroData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_stations, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = MetroData[position]
        val zip = ZipData[position]
        holder.bindData(station, zip)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private var view: View = itemView
        private var station: String? = null
        private var zip: String? = null

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(itemView: View) {
            // plesae turn to landmark Activity here
            Toast.makeText(itemView.context, "station: $station , zipcode: $zip",Toast.LENGTH_LONG).show()
        }
        fun bindData(station: String, zip: String){
            this.station = station
            this.zip = zip
            view.stationName.text = this.station
        }

    }

    fun filterList(newstationList : ArrayList<String>, newZipList: ArrayList<String>){
        MetroData = newstationList
        ZipData = newZipList
        notifyDataSetChanged()
    }

}