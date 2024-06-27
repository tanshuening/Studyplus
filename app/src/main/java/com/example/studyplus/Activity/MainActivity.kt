package com.example.studyplus.Activity


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.example.studyplus.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


    }

    fun goToLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }


}

