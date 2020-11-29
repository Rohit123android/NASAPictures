package com.obvious.nasapic.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.obvious.nasapic.model.NASAPicture
import com.obvious.nasapic.repositories.NASAPictureRepository

/**
 * @author Rohit Prabhakarn
 *
 * NASAPictureViewModel to take date from repository and pass to Ui and having life cycle aware
 */

class NASAPictureViewModel(val app:Application):AndroidViewModel(app) {
    var pictures: MutableLiveData<List<NASAPicture>> = MutableLiveData()
    var nasaPictureRepository:NASAPictureRepository

    init {
        nasaPictureRepository = NASAPictureRepository.getInstance(app.applicationContext)

    }


    // get List of NASA picture object
  fun getAllNASAPicture():LiveData<List<NASAPicture>> {

          pictures = nasaPictureRepository.getAllPicture(app.applicationContext)
      return pictures
  }

}