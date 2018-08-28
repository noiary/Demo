package com.maxmao.network.internal;

import com.maxmao.network.Config;
import com.maxmao.tvproject.repositorie.WebService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Network
 * Created by maodq on 2017/12/25.
 */

public class RetrofitUtil {
    private static Retrofit retrofit;
    private static final Object TAG = "RetrofitUtil";
    private static WebService webService;

    public static WebService webService() {
        if (webService == null) {
            synchronized (RetrofitUtil.class) {
                if (webService == null) {
                    webService = createWebService();
                }
            }
        }
        return webService;
    }

    // 重置retrofit
    public static void resetRetrofit() {
        retrofit = null;
        webService = null;
    }

    private static WebService createWebService() {
        return getRetrofit().create(WebService.class);
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    initRetrofit();
                }
            }
        }
        return retrofit;
    }

    private static void initRetrofit() {
//        CookiePersistor spcp = new SharedPrefsCookiePersistor(App.getApplication());
//        ClearableCookieJar cookieJar = new MyPersistentCookieJar(new SetCookieCache(), spcp) {
//            private final String url = "login_cookie";
//
//            @Override public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                if (url.toString().contains("login")) {
//                    super.saveFromResponse(url, cookies);
//                }
//            }
//
//            @Override public synchronized List<Cookie> loadForRequest(HttpUrl url) {
//                return super.loadForRequest(url);
//            }
//        };
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
//                .cookieJar(cookieJar)
                .addInterceptor(new LogInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.getInstance().getUrl())
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public static <T> T call(Call<T> call) {
        T result = null;
        try {
            Response<T> response = call.execute();
            result = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
