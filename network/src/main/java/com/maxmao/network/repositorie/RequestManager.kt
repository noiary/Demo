package com.maxmao.tvproject.repositorie

import com.blankj.utilcode.util.ToastUtils
import com.maxmao.network.BBean
import com.maxmao.network.R
import io.reactivex.Emitter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import java.io.IOException

class RequestManager<T : BBean<*>>(val api: Call<T>) {
    private var onSuccess: Consumer<T>? = null
    private var onError: Consumer<in Throwable>? = null
    private var onComplete: Action? = null
    private var onSubscribe: Consumer<in Disposable>? = null
    private var onFinally: Action? = null

    fun onSuccess(onSuccess: Consumer<T>): RequestManager<T> {
        this.onSuccess = onSuccess
        return this
    }

    fun onError(onError: Consumer<in Throwable>): RequestManager<T> {
        this.onError = onError
        return this
    }

    fun onComplete(onError: Consumer<in Throwable>): RequestManager<T> {
        this.onComplete = onComplete
        return this
    }

    fun onSubscribe(onError: Consumer<in Throwable>): RequestManager<T> {
        this.onSubscribe = onSubscribe
        return this
    }


    fun start() {
        var observable = Observable
                .create(ObservableOnSubscribe<T> {
                    try {
                        val response = api.execute()
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

        if (onFinally != null) {
            observable = observable.doAfterTerminate(onFinally)
        }

        observable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe(object : Observer<T> {
            override fun onComplete() {
                onComplete?.run()
            }

            override fun onSubscribe(d: Disposable) {
                onSubscribe?.accept(d)
            }

            override fun onNext(t: T) {
                onSuccess?.accept(t)
            }

            override fun onError(e: Throwable) {
                onError?.accept(e)
            }
        })
    }


    private fun check(bean: BBean<*>?, emitter: Emitter<T>): Boolean {
        if (bean == null) {
            emitter.onError(NullPointerException("bean is null"))
            return false
        }
//        if (bean.code == -100) {
//            UIUtil.post(Runnable {
//                LoginActivity.launch(null)
//            })
//            return false
//        }
        return bean.data != null
    }
}