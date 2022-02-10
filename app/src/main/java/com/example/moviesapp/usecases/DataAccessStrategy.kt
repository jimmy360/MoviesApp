package com.example.moviesapp.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.moviesapp.framework.ui.common.Resource
import kotlinx.coroutines.Dispatchers

class DataAccessStrategy {

    companion object {

        fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                                       networkCall: suspend () -> Resource<A>,
                                       saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
            liveData(Dispatchers.IO) {
                emit(Resource.loading())
                val source = databaseQuery.invoke().map { Resource.success(it) }
                emitSource(source)

                val responseStatus = networkCall.invoke()
                if (responseStatus.status == Resource.Status.SUCCESS) {
                    saveCallResult(responseStatus.data!!)

                } else if (responseStatus.status == Resource.Status.ERROR) {
                    emit(Resource.error(responseStatus.message!!))
                    emitSource(source)
                }
            }
    }



}