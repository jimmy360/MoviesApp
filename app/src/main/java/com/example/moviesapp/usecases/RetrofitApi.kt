package com.example.moviesapp.usecases

import com.example.moviesapp.framework.ui.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

   companion object {

       private val retrofit = Retrofit.Builder()
           .baseUrl(Constants.URL_BASE)
           .addConverterFactory(GsonConverterFactory.create())
           .build()

       //Create service generic
       fun <S> CreateService(service_class: Class<S>) : S {
           return retrofit.create(service_class)
       }
   }


}