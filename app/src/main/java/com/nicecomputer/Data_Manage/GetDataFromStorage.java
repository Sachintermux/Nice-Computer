package com.nicecomputer.Data_Manage;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nicecomputer.AdapterModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GetDataFromStorage {

    public void saveData( ArrayList<AdapterModel> adapterModels, SharedPreferences sharedPreferences ) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(adapterModels);
        editor.putString("task list", json);
        editor.apply();

    }

    public ArrayList<AdapterModel> readData( ArrayList<AdapterModel> adapterModels, SharedPreferences sharedPreferences ) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<AdapterModel>>() {
        }.getType();
        adapterModels = gson.fromJson(json, type);
        if (adapterModels == null) {
            adapterModels = new ArrayList<>();
        }
        return adapterModels;

    }

}
