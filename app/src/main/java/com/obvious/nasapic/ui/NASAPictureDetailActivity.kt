package com.obvious.nasapic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.obvious.nasapic.R
import com.obvious.nasapic.adapter.NASAPictureAdaper
import com.obvious.nasapic.adapter.NASAPicturePagerAdapter
import com.obvious.nasapic.constants.NASAPicConstant
import com.obvious.nasapic.model.NASAPicture
import com.obvious.nasapic.viewmodel.NASAPicViewModelProviderFactory
import com.obvious.nasapic.viewmodel.NASAPictureViewModel

/**
 * @author Rohit Prabhakarn
 *
 * screen to show detail of NASA pic with  image ,date, title and  explanation
 * and also provide swiping through other images
 */

class NASAPictureDetailActivity : AppCompatActivity() {

    //view model variable declaration
    lateinit var mNASAPicViewModel:NASAPictureViewModel

    // list of NASA pic declaration
    lateinit var pictureList:List<NASAPicture>

    //initiate all views  and setup pages of NASA pic images
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_picture_detail)

        var actionbar =supportActionBar
        actionbar!!.displayOptions= ActionBar.DISPLAY_SHOW_CUSTOM
        actionbar.setCustomView(R.layout.actionbar_detail_screen)

        //get selected image position from the sorted list of images
        val selectedPic = intent.getIntExtra(NASAPicConstant.KEY_SELECTED_IMAGE,0)

        //used viewmodel to take list of NASA picture to make it simple
        var picNASAViewModelProviderFactory = NASAPicViewModelProviderFactory(application)
        mNASAPicViewModel = ViewModelProvider(this,picNASAViewModelProviderFactory).get(
            NASAPictureViewModel::class.java)

        //picture list fetched from viewmodel
        pictureList=mNASAPicViewModel.getAllNASAPicture().value!!

        // viewpager setup with pager adapter and also set current page as selected image
        val viewPager:ViewPager = findViewById(R.id.picture_viewpager)
        viewPager.adapter = NASAPicturePagerAdapter(this,pictureList)
        viewPager.currentItem=selectedPic

    }
}