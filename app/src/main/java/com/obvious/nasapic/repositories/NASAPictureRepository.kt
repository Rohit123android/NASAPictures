package com.obvious.nasapic.repositories

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obvious.nasapic.constants.NASAPicConstant
import com.obvious.nasapic.model.NASAPicture
import com.obvious.nasapic.utils.PicUtils
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean


/**
 * @author Rohit Prabhakarn
 *
 * respository to  implement all database method  what we have defined in NASAPictureDao
 */

class NASAPictureRepository private constructor(context: Context) {


    /**
     * create instance of repository object
     */
    companion object {
        lateinit var INSTANCE: NASAPictureRepository
        lateinit var pictures: MutableLiveData<List<NASAPicture>>

        fun getInstance(context: Context): NASAPictureRepository {
            INSTANCE = NASAPictureRepository(context)
            return INSTANCE


        }
    }

    /**
     * get all the picture and pass it to ViewModel layer
     */
          fun getAllPicture(context: Context): MutableLiveData<List<NASAPicture>> {
            var pictureList = getPictureList(context)
            pictures =MutableLiveData()
            pictures.value =pictureList

            return pictures

        }


    /**
     * take  List of NASAPicture as a result from json file
     */

         fun getPictureList(context: Context): List<NASAPicture> {
            val jsonFileString = getJsonDataFromAsset(context, NASAPicConstant.NASAPicJsonFile)

            val gson = Gson()
            val listPictureType = object : TypeToken<List<NASAPicture>>() {}.type

            var pictures: List<NASAPicture> = gson.fromJson(jsonFileString, listPictureType)
            return PicUtils.sortListByDate(pictures)
        }


    /**
     * get json string from json file.
     */

        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }


    }