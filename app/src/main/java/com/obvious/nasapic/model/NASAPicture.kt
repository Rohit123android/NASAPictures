package com.obvious.nasapic.model

import java.io.Serializable

/**
 * @author Rohit Prabhakarn
 *
 *  Model class which is use to map json object data
 *
 */

data class NASAPicture(

                   val copyright: String,
                   val date: String,
                   val explanation: String,
                   val hdurl: String,
                   val media_type: String,
                   val service_version: String,
                   val title: String,
                   val url: String
                   ) :Serializable
