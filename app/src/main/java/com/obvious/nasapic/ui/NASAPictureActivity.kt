package com.obvious.nasapic.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obvious.nasapic.R
import com.obvious.nasapic.adapter.NASAPictureAdaper
import com.obvious.nasapic.constants.NASAPicConstant
import com.obvious.nasapic.interfaces.PicOnClickedListener
import com.obvious.nasapic.model.NASAPicture
import com.obvious.nasapic.viewmodel.NASAPicViewModelProviderFactory
import com.obvious.nasapic.viewmodel.NASAPictureViewModel

/**
 * @author
 * NASA picture app launcher screen that displays List of NASA images in a recyclerview
 *
 */

class NASAPictureActivity : AppCompatActivity(), PicOnClickedListener {

    //viewmodel variable
    lateinit var mNASAPicViewModel:NASAPictureViewModel

    /**
     * instantiate layout and views
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actionbar =supportActionBar
        actionbar!!.displayOptions=ActionBar.DISPLAY_SHOW_CUSTOM
        actionbar.setCustomView(R.layout.actionbar_grid_screen)

        // recyclerview
        val recyclerView: RecyclerView = findViewById(R.id.pic_recyclerview)

        //set linear layout manager with  list vertical view
        val layoutManager= LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=layoutManager

        // viewmodel instantiation

        var picNASAViewModelProviderFactory =NASAPicViewModelProviderFactory(application)
        mNASAPicViewModel = ViewModelProvider(this,picNASAViewModelProviderFactory).get(NASAPictureViewModel::class.java)

        //set data to NASA pic adapter  and set the adapter to recyclerview
        var NASApicAdapter = NASAPictureAdaper(mNASAPicViewModel.getAllNASAPicture().value!!,this)
        recyclerView.adapter=NASApicAdapter


        //get data from the view model  and observe the data
        mNASAPicViewModel.getAllNASAPicture().observe(this,object:Observer<List<NASAPicture>>{
            override fun onChanged(t: List<NASAPicture>?) {
                NASApicAdapter.notifyDataSetChanged()
            }

        })


    }

    /**
     *  on NASA pic on click, launch NASA detail screen with selected position
     */
    override fun picOnClick(position: Int) {
             var intent= Intent(this, NASAPictureDetailActivity::class.java)
       intent.putExtra(NASAPicConstant.KEY_SELECTED_IMAGE,position)
      startActivity(intent)
    }

}