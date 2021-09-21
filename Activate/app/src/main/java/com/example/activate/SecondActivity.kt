package com.example.activate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.TextView




class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val user = "ЖЫвотное"
        val gift = "дырку от бублика"

        val infoTextView = findViewById<TextView>(R.id.textView)
        infoTextView.text = "$user , вам передали $gift"
    }
}