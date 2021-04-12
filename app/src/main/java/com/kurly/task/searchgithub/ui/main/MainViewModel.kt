package com.kurly.task.searchgithub.ui.main

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurly.task.searchgithub.model.RepositoryModel
import com.kurly.task.searchgithub.service.RetrofitService
import com.kurly.task.searchgithub.util.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _keyword = MutableLiveData<String>()
    val keyword: MutableLiveData<String>
        get() = _keyword

    private val _githubRepositories = MutableLiveData<List<RepositoryModel>>()
    val githubRepositories = _githubRepositories

    private val _progress = MutableLiveData<Int>()
    val progress: MutableLiveData<Int>
        get() = _progress


    private val _hideKeyboard = MutableLiveData<Event<Boolean>>()
    val hideKeyboard: MutableLiveData<Event<Boolean>>
        get() = _hideKeyboard

    private val _showEmptyToast = MutableLiveData<Event<Boolean>>()
    val showEmptyToast: MutableLiveData<Event<Boolean>>
        get() = _showEmptyToast

    private val _showErrorToast = MutableLiveData<Event<Boolean>>()
    val showErrorToast: MutableLiveData<Event<Boolean>>
        get() = _showErrorToast

    init {

        _progress.value = View.GONE
    }


    fun clickSearch() {

        _progress.value = View.VISIBLE
        _hideKeyboard.value = Event(true)

        if (_keyword.value.isNullOrBlank() || _keyword.value!!.trim().isEmpty()) {

            _progress.value = View.GONE
            _showEmptyToast.value = Event(true)
        } else {

            RetrofitService.client.getRepositories(_keyword.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    _progress.value = View.GONE
                    _githubRepositories.postValue(it.items)
                }, {

                    _progress.value = View.GONE
                    showErrorToast.value = Event(true)
                })
        }
    }
}