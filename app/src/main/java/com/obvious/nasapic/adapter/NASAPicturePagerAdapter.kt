package com.obvious.nasapic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.obvious.nasapic.R
import com.obvious.nasapic.model.NASAPicture
import kotlinx.android.synthetic.main.picture_detail_item.view.*

/**
 * @author Rohit Prabhakarn
 *
 * NASAPicturePagerAdapter  is a pager adapter class implemented  for
 * swiping images at NASA image detail screen
 * its a parameterized constructor having activity context and List of NASAPicture images
 * as a parameter
 */

class NASAPicturePagerAdapter(var context: Context, var pictureList:List<NASAPicture>):PagerAdapter() {

    override fun getCount(): Int {
        return pictureList.size
    }

    /**
     * instantiate layout for picture detail screen and bind data into the view
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val mNASApicture = pictureList[position]
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.picture_detail_item, container, false) as ViewGroup
        layout.apply {
            Glide.with(this).load(mNASApicture.url).into(detail_img)
            pic_title.text =mNASApicture.title
            pic_desc.text =mNASApicture.explanation
            pic_date.text=mNASApicture.date
        }
        container.addView(layout)
        return layout
    }

    /**
     *return true and our page will display
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

//destroy page item
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}