package com.maxmao.network;

import com.blankj.utilcode.util.SPUtils;
import com.maxmao.network.internal.RetrofitUtil;

public class Config {
    private static final String CONFIG_URL = "config_url";
    private String url;

    private Config() {
        url = SPUtils.getInstance().getString(CONFIG_URL, Constant.URL);
    }

    public static Config getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 动态切换网络环境
     *
     * @param url 新的环境地址
     */
    public void config(String url) {
        getInstance().url = url;
        SPUtils.getInstance().put(CONFIG_URL, url);
        RetrofitUtil.resetRetrofit();
    }

    public String getUrl() {
        return url;
    }


    private static class Holder {
        private static Config INSTANCE = new Config();
    }
}
