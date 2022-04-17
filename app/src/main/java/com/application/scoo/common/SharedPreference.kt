package com.application.scoo.common

import android.content.Context
import android.content.SharedPreferences
import com.application.scoo.AppBase
import com.application.scoo.data.model.UserDetails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SharedPreference(val context: AppBase) {
    private val PREFS_NAME = "com.suiteup.consumer"
    val sharedPref: SharedPreferences =
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, "")
    }

    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun <UserDetails> setList(key: String?, list: List<UserDetails>?) {
        val gson = Gson()
        val json = gson.toJson(list)
        set(key, json)
    }

    operator fun set(key: String?, value: String?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.commit()
    }


    fun getList(KEY_NAME: String): List<UserDetails?>? {
        val arrayItems: List<UserDetails>
        val serializedObject: String? = sharedPref.getString(KEY_NAME, null)
        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<List<UserDetails?>?>() {}.type
            arrayItems = gson.fromJson<List<UserDetails>>(serializedObject, type)
            return arrayItems
        }
        return null
    }


}