package com.maxmao.tvproject.repositorie

import com.blankj.utilcode.util.ToastUtils
import com.maxmao.tvproject.R
import com.maxmao.tvproject.data.bean.BBean
import com.maxmao.tvproject.login.LoginActivity
import com.maxmao.tvproject.util.RetrofitUtil
import com.maxmao.tvproject.util.UIUtil
import io.reactivex.Emitter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import java.io.IOException

open abstract class BRepository<T : BBean<*>>/*(val id: String)*/ {
    fun requestData(onNext: Consumer<T>) {
        requestData(onNext, null, null, null)
    }

    fun requestData(onNext: Consumer<T>,
                    onError: Consumer<in Throwable>) {
        requestData(onNext, onError, null, null)
    }

    fun requestData(onNext: Consumer<T>,
                    onError: Consumer<in Throwable>,
                    onComplete: Action) {
        requestData(onNext, onError, onComplete, null)
    }

    fun requestData(onNext: Consumer<T>,
                    _onError: Consumer<in Throwable>?,
                    _onComplete: Action?,
                    _onSubscribe: Consumer<in Disposable>?) {

        var onError = _onError
        var onComplete = _onComplete
        var onSubscribe = _onSubscribe

        if (onError == null) {
            onError = Functions.ON_ERROR_MISSING
        }

        if (onComplete == null) {
            onComplete = Functions.EMPTY_ACTION
        }

        if (onSubscribe == null) {
            onSubscribe = Functions.emptyConsumer<Any>()
        }


        Observable
                .create(ObservableOnSubscribe<T> {
//                    val cacheBean = cache()
//                    if (cacheBean != null) {
//                        it.onNext(cacheBean)
//                        it.onComplete()
//                        return@ObservableOnSubscribe
//                    }

                    try {
                        val response = getApi().execute()
                        if (response.isSuccessful) {
                            val bean = response.body()
                            if (check(bean, it)) {
//                                cache(bean!!)
                                it.onNext(bean!!)
                            }
                        }
                    } catch (e: Exception) {
                        if (e is IOException) {
                            ToastUtils.showLong(R.string.net_error)
                        }
                        it.onError(e)
                        e.printStackTrace()
                    } finally {
                        it.onComplete()
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onComplete, onSubscribe)
    }

    protected fun webService(): WebService {
        return RetrofitUtil.webService()
    }


    abstract fun getApi(): Call<T>

    private fun check(bean: BBean<*>?, emitter: Emitter<T>): Boolean {
        if (bean == null) {
            emitter.onError(NullPointerException("bean is null"))
            return false
        }

        if (bean.code == -100) {
            UIUtil.post(Runnable {
                LoginActivity.launch(null)
            })
            return false
        }
        return bean.data != null
    }

//    private fun cache(): T? {
//        return CacheMemoryUtils.getInstance().get(id)
//    }
//
//    private fun cache(data: T) {
//        CacheMemoryUtils.getInstance().put(id, data)
//    }


    interface Callback<T> {
        fun call(t: T)
    }
}