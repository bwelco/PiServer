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

public class DoorSpUtil {


    public static final String DOOR_TABLE_NAME = "door_table_name";
    public static final String DOOR_TABLE_KEY = "door_table_key";
    static SharedPreferences sharedPreferences;

    public static class DoorEvent {
        @SerializedName("userName")
        public String userName;

        @SerializedName("time")
        public long time;
    }

    static {
        sharedPreferences = PiAPP.applicationContext
                .getSharedPreferences(DOOR_TABLE_NAME, Activity.MODE_PRIVATE);
    }


    public static List<DoorEvent> getDoorEvents() {
        String doorEventsStr = sharedPreferences.getString(DOOR_TABLE_KEY, "");
        List<DoorEvent> doorEvents = new Gson().fromJson(doorEventsStr,
                new TypeToken<List<DoorEvent>>() {
                }.getType());
        if (doorEvents == null) {
            doorEvents = new ArrayList<>();
        }

        return doorEvents;
    }

    public static void saveDoorEvent(List<DoorEvent> doorEvents) {
        sharedPreferences.edit().putString
                (DOOR_TABLE_KEY, new Gson().toJson(doorEvents)).commit();
    }

    public static void addDoorEvent(DoorEvent doorEvent) {
        List<DoorEvent> doorEvents = getDoorEvents();
        doorEvents.add(doorEvent);
        saveDoorEvent(doorEvents);
    }

    public void clearEvent() {
        sharedPreferences.edit().putString
                (DOOR_TABLE_KEY, "").commit();
    }
}
