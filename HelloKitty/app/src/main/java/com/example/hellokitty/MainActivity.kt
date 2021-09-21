package com.example.hellokitty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mHelloTextView: TextView
    private lateinit var editText: EditText
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHelloTextView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)
        var imageButton: ImageButton = findViewById(R.id.imageButton)


        imageButton.setOnClickListener {

            if (editText.text.isEmpty()) {
                textView.text = "Hello Kitty!";
            } else {
                textView.text = "Привет, " + editText.text
            }
        }
    }
}
