package com.example.sunka.metroexplorer

import android.content.Context
import android.nfc.Tag
import android.os.Parcelable
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.sunka.metroexplorer.model.Metro
import kotlinx.android.synthetic.main.activity_metro_station.*
import kotlinx.android.synthetic.main.row_stations.view.*
import java.nio.charset.Charset
import java.security.AlgorithmConstraints


/**
 * Created by sunka on 2017/9/29.
 */

// recycler View adapter
class MetroStationListAdapter(private var context: Context, private var MetroData: ArrayList<String>) : RecyclerView.Adapter<MetroStationListAdapter.ViewHolder>(){

    private var TAG: String = "MetroStationAdapter"
  //  lateinit var myfilter : TestFilter




    override fun getItemCount() = MetroData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_stations, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = MetroData[position]
        holder.itemView.stationName.text = station

    }

    /*override fun getFilter(): Filter {              // SearchView search filter

        myfilter = TestFilter()

        return myfilter
    }*/

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


    }

    fun filterList(newList : ArrayList<String>){
        MetroData = newList
        notifyDataSetChanged()
    }

    /*inner class TestFilter : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var newList = ArrayList<String>();
            if(constraint != null && constraint.isNotEmpty()){
                for(i in 0..MetroData.size-1){
                    val content = MetroData.get(i)
                    if(content.contains(constraint)){
                        newList.add(MetroData.get(i))
                    }
                }
            }else{
                newList = MetroData
            }
            val filterResult = FilterResults()
            filterResult.count = newList.size
            filterResult.values = newList
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            if(results!=null && results.count > 0){
                MetroData = results.values as ArrayList<String>
                notifyDataSetChanged()
            }else{
                MetroData = ArrayList<String>()
                MetroData.add("Not Found")
                notifyDataSetChanged()
            }
        }
    }*/
}