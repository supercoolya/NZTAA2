package com.example.nztaa2.Utils;

import com.google.gson.Gson;
public class GsonUtils {

    private static class GsonHolder {
        public static final Gson instance = new Gson();
    }

    public static Gson getInstance() {
        return GsonHolder.instance;
    }
}

