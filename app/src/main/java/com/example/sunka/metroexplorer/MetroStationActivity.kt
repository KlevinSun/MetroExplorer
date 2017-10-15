package com.example.sunka.metroexplorer


import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher

import android.view.Menu
import android.widget.EditText

import android.widget.ProgressBar
import android.widget.SearchView

import com.example.sunka.metroexplorer.model.Metro
import kotlinx.android.synthetic.main.activity_metro_station.*
import java.util.Locale.filter

class MetroStationActivity : AppCompatActivity(), WmataStationSearchManager.StationSearchCompletionListener {
    lateinit private var linearLayoutManager: LinearLayoutManager
    lateinit private var adapter: MetroStationListAdapter
    lateinit var wmataStationSearchManager: WmataStationSearchManager
    private var metroData = ArrayList<Metro>()
    private var metroStationNameList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro_station)

        showProgress(true)

        linearLayoutManager = LinearLayoutManager(this)         // show station list on the recycler view
        adapter = MetroStationListAdapter(this, metroStationNameList)
        wmataStationSearchManager = WmataStationSearchManager(this, metroData)
        wmataStationSearchManager.stationSearchCompletionListener = this
        wmataStationSearchManager.search()


        searchEditView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

    }

    override fun onResume() {
        super.onResume()
        searchEditView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
    }

    fun showProgress(show: Boolean) {
        if (show) {

            progressBar.visibility = ProgressBar.VISIBLE
        } else {

            progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    override fun searchSucceed() {
        if (metroData.isNotEmpty()) {
            for (i in 0..metroData.size - 1) {
                if (!metroStationNameList.contains(metroData.get(i).station)) {
                    metroStationNameList.add(metroData.get(i).station)
                }
            }
            showProgress(false)

            MetroStationList.layoutManager = linearLayoutManager
            MetroStationList.adapter = adapter
        } else {

            wmataStationSearchManager.search()
        }
    }

    override fun searchFailed() {
        wmataStationSearchManager.search()
    }

    private fun filter(text: String) {
        val newList = ArrayList<String>();
        if (metroStationNameList.isNotEmpty() && text.isNotEmpty()) {
            for (i in 0..metroStationNameList.size - 1) {
                val content = metroStationNameList[i]
                if (content.toLowerCase().contains(text.toLowerCase())) {
                    newList.add(metroStationNameList.get(i))
                }
            }

            adapter.filterList(newList)
        }
    }


    /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {            //attach searchView to this activity
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.menuSearch)?.actionView as SearchView

        searchView.isIconified = false

        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
       return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {         // filter recycler view when searchView input is not null
        adapter.filter.filter(newText)
        return false
    }
*/
}


