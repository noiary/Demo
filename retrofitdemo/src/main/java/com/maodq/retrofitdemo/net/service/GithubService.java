package com.maodq.retrofitdemo.net.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * https://api.github.com/users/{user}/repos
 * Created by maodq on 16/6/5.
 */
public interface GithubService {
    @GET("users/{user}/repos")
    Call<String> queryRepos(@Path("user") String user);
}
