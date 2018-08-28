package com.maxmao.tvproject.repositorie

import com.maxmao.tvproject.data.bean.BBean
import retrofit2.Call

open abstract class BRepository2<T : BBean<*>>/*(val id: String)*/ {
    protected abstract fun getApi(): Call<T>

    fun request(): RequestManager<T> {
        return RequestManager(getApi())
    }
}