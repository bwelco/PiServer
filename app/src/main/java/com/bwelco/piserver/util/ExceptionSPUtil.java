package com.bwelco.piserver.util;

import android.app.Activity;
import android.content.SharedPreferences;

import com.bwelco.piserver.PiAPP;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwelco on 2017/5/19.
 */

public class ExceptionSPUtil {

    public static final String EXCEPTION_TABLE_NAME = "exception_table_name";
    public static final String EXCEPTION_TABLE_KEY = "exception_table_key";
    static SharedPreferences sharedPreferences;

    public static class ExceptionBean {
        @SerializedName("errType")
        public String errType;

        @SerializedName("userName")
        public String userName;

        @SerializedName("time")
        public long time;
    }

    static {
        sharedPreferences = PiAPP.applicationContext
                .getSharedPreferences(EXCEPTION_TABLE_NAME, Activity.MODE_PRIVATE);
    }

    public static List<ExceptionBean> getExceptionList() {
        String listStr = sharedPreferences.getString(EXCEPTION_TABLE_KEY, "");
        List<ExceptionBean> list = new Gson().fromJson(listStr,
                new TypeToken<List<ExceptionBean>>() {
                }.getType());
        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }

    public static void saveExceptionList(List<ExceptionBean> list) {
        sharedPreferences.edit().
                putString(EXCEPTION_TABLE_KEY, new Gson().toJson(list)).commit();
    }

    public static void addExceptionEvent(ExceptionBean exceptionBean) {
        List<ExceptionBean> list = getExceptionList();
        list.add(exceptionBean);
        saveExceptionList(list);
    }

    public static void clearExceptions() {
        sharedPreferences.edit().putString(EXCEPTION_TABLE_KEY, "").commit();
    }
}
