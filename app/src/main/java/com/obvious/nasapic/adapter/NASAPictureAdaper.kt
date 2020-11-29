package com.obvious.nasapic.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obvious.nasapic.R
import com.obvious.nasapic.interfaces.PicOnClickedListener
import com.obvious.nasapic.model.NASAPicture
import kotlinx.android.synthetic.main.picture_item.view.*

/**
 * @author Rohit Prabhakarn
 *
 * RecyclerView adapter to show list of NASA picture
 * its a parameterized constructor which  takes to two parameter
 * List of NASA picture object and picOnclickListener context
 */
class NASAPictureAdaper(var pictureList:List<NASAPicture>,var picOnClickedListener: PicOnClickedListener):RecyclerView.Adapter<NASAPictureAdaper.NASAPicViewHolder>() {


    //NASAPicViewHolder view holder class of NASA picture
    inner class NASAPicViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    //oncreate view holder method to return viewHolder object with configured layout
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NASAPicViewHolder {

       return NASAPicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.picture_item,parent,false))
    }


    //bind view with data and also onclick listener for the view
    override fun onBindViewHolder(holder: NASAPicViewHolder, position: Int) {

        val picture =pictureList[position]
        holder.itemView.apply {
            Glide.with(this).load(picture.url).into(pic_nasa)
            pic_list_date.text =picture.date

            }
        holder.itemView.setOnClickListener{
            picOnClickedListener.picOnClick(position)
        }
    }

   //picture count
    override fun getItemCount(): Int {
return pictureList.size
    }

}