package com.obvious.nasapic.utils

import com.obvious.nasapic.model.NASAPicture
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 *
 */
class PicUtils {

    /**
     * utility class with some function
     * one to convert date string object   into LocalDate object
     * second one to sort List of NASA picture according to date means latest
     * date pic will show first
     */

    companion object
    {
        //convert date string into local date object
        fun convertStringToDate(datetime: String):LocalDate
        {
            val date = LocalDate.parse(datetime, DateTimeFormatter.ISO_DATE)

           return date
        }

        //sort list of picture by date
        fun sortListByDate(picList:List<NASAPicture>):List<NASAPicture>
        {
            Collections.sort(picList,
                Comparator { o1, o2 ->
                    if (convertStringToDate(o1.date) == null || convertStringToDate(o2.date) == null) 0 else convertStringToDate(o2.date)
                        .compareTo(convertStringToDate(o1.date))
                })
            return picList
        }


    }
}