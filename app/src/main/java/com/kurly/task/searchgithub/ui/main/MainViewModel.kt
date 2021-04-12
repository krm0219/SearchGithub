package com.kurly.task.searchgithub.ui.main

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
    // TODO: Implement the ViewModel

    private val _keyword = MutableLiveData<String>()
    val keyword: MutableLiveData<String>
        get() = _keyword

    private val _githubRepositories = MutableLiveData<List<RepositoryModel>>()
    val githubRepositories = _githubRepositories


    private val _hideKeyboard = MutableLiveData<Event<Boolean>>()
    val hideKeyboard: MutableLiveData<Event<Boolean>>
        get() = _hideKeyboard


    fun clickSearch() {

        _hideKeyboard.value = Event(true)

        if (_keyword.value.isNullOrBlank() || _keyword.value!!.trim().isEmpty()) {

        } else {

            RetrofitService.client.getRepositories(_keyword.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    _githubRepositories.postValue(it.items)

//                    progressVisible.value = false
//                    Log.e(RetrofitClient.TAG, "${it.resultCode} / ${it.resultMsg}")
//
//                    if (it.resultCode == 0) {
//
//                        Preferences.userName = name
//                        Preferences.userEmail = email
//                    }
//
//                    _resultAlert.value = it

                }, {
//                    progressVisible.value = false
//                    _errorAlert.value = R.string.msg_network_connect_error

                })


            CoroutineScope(Dispatchers.IO).launch {
//                githubRepository.getRepositories(query)?.let {
//                        response -> if(response.isSuccessful) {
//                    response.body()?.let { _githubRepositories.postValue(it.items) } } } }

            }
        }

    }
    }