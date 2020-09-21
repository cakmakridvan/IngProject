package com.example.demoproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoproject.model.ModelData
import com.example.demoproject.services.ApiUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(): ViewModel() {


    private val dataApiService = ApiUrl()
    private val disposable = CompositeDisposable()

    val data = MutableLiveData<List<ModelData>>()
    val dataError =  MutableLiveData<Boolean>()
    val dataLoading = MutableLiveData<Boolean>()
    fun refreshData(name: String){
        getDataFromApi(name)
    }

    private fun getDataFromApi(name: String) {
        dataLoading.value= true

        disposable.add(
            dataApiService.getData(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ModelData>>(){
                    override fun onSuccess(t: List<ModelData>) {
                        data.value=t
                        dataError.value=false
                        dataLoading.value=false

                    }

                    override fun onError(e: Throwable) {
                        dataLoading.value=false
                        dataError.value=true
                        e.printStackTrace()

                    }

                })
        )
    }
}