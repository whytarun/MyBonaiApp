package com.example.mybonaiapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(isOnline()) {
            setupFragment()
        }
    }

    private fun setupFragment(){
        val fragment =RecylerListFragment.newInstance()
        val fragmentManager :FragmentManager =supportFragmentManager
        val fragmentTransaction :FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content,fragment)
        fragmentTransaction.commit()
    }

    fun isOnline() :Boolean {
        val ConnectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            //Toast.makeText(this@MainActivity, "Network Available", Toast.LENGTH_LONG).show()
            return true
        } else {
            Toast.makeText(this@MainActivity, "Network Not Available", Toast.LENGTH_LONG).show()
            return false
        }
    }
}