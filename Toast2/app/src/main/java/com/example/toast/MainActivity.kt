package com.example.toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
//        button.setOnClickListener {
//            val toast = Toast.makeText(applicationContext, R.string.cat_food, Toast.LENGTH_LONG)
//            toast.setGravity(Gravity.CENTER, 0, 0)
//
//            val toastContainer = toast.view as LinearLayout
//            val catImage = ImageView(this)
//            catImage.setImageResource(R.drawable.img)
//            toastContainer.addView(catImage, 0)
//            toast.show()
//        }
        button.setOnClickListener {
            val inflater = layoutInflater
            val container = findViewById<ViewGroup>(R.id.custom_toast_container)
            val layout: View = inflater.inflate(R.layout.custom_toast, container)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = "Пора покормить кота!"
            with (Toast(applicationContext)) {
                setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                duration = Toast.LENGTH_LONG
                view = layout
                show()
            }
        }

    }
}