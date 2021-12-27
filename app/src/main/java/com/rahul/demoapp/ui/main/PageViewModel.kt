package com.rahul.demoapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahul.demoapp.MainRepository
import com.rahul.demoapp.model.MovieListModel
import kotlinx.coroutines.*

class PageViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _index = MutableLiveData<Int>()
    var tabIndex = 0;
    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<MovieListModel>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun setIndex(index: Int) {
        _index.value = index
        tabIndex = index
    }
    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = if (tabIndex == 1) mainRepository.getAllMovies() else mainRepository.getUpcomingMovies()
            println(response)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    println(response.body())
                    movieList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        print(message)
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}