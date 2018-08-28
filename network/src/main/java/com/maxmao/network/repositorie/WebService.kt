package com.maxmao.tvproject.repositorie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    /**
     * 登录
     */
    @GET("login")
    fun login(@Query("user_name") username: String,
              @Query("user_pwd") password: String
    ): retrofit2.Call<*>

    /**
     * getMediaGroup
     */
    @GET("getMediaGroup")
    fun getMediaGroup(): Call<*>


//    @GET("getMediaListOfGroup")
//    fun getMediaListOfGroup():Call<>


    /** 获取热门节目列表 */
    @GET("getHotMediaList")
    fun getHotMediaList(@Query("offset") offset: String,
                        @Query("count") count: String)

    /** 根据节目分组id获取节目列表 */
    @GET("getMediaListOfGroup")
    fun getMediaListOfGroup(@Query("group_id") id: String): Call<*>


}