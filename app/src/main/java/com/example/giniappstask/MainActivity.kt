package com.example.giniappstask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.giniappstask.fragments.NumbersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NumbersFragment())
            .commit()
    }
}
