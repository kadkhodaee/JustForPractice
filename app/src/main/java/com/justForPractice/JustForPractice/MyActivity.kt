package com.justForPractice.JustForPractice

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class MyActivity : AppCompatActivity(){

    companion object{
        var MySetting:Boolean =true
    }
    val SharedPrefFile ="MySetting"

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences:SharedPreferences=this.getSharedPreferences(SharedPrefFile, Context.MODE_PRIVATE)
        MySetting=sharedPreferences.getBoolean("MyTheme",true)
        if(MySetting){
            setTheme(R.style.AppTheme_NoActionBar)
        }else{
            setTheme(R.style.AppDark_NoActionBar)
        }
        super.onCreate(savedInstanceState)
    }
    fun changeTheme(){
        val sharedPreferences:SharedPreferences=this.getSharedPreferences(SharedPrefFile, Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        MySetting=sharedPreferences.getBoolean("MyTheme",true)
        MySetting = MySetting==false
        editor.putBoolean("MyTheme",MySetting)
        editor.apply()
        editor.commit()
        recreate()
    }
}