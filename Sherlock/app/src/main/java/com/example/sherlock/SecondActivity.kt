package com.example.sherlock
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup

const val THIEF = "com.example.sherlock.THIEF"

class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        val radioGroup : RadioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { _, optionId ->
            val answerIntent = Intent()
            when (optionId) {
                R.id.radio_dog -> answerIntent.putExtra(THIEF, "Сраный пёсик")
                R.id.radio_crow -> answerIntent.putExtra(THIEF, "Ворона")
                R.id.radio_cat -> answerIntent.putExtra(THIEF, "Лошадь Пржевальского")
            }

            setResult(RESULT_OK, answerIntent)
            finish()
        }
    }
}