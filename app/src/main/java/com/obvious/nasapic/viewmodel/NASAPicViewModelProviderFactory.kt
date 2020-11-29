package com.obvious.nasapic.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Rohit Prabhakarn
 *NASAPicViewModelProviderFactory is used to provide viewModel object
 */

class NASAPicViewModelProviderFactory(var app:Application) :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NASAPictureViewModel(app) as T
    }
}