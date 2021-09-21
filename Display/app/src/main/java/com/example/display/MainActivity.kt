package com.example.display

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  private  lateinit var seekBar: SeekBar
  private  lateinit var textView: TextView
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        seekBar=findViewById(R.id.seekBar)
        textView=findViewById(R.id.textView)
        val brightness = getBrightness()
        seekBar.progress = brightness

        textView.text = "Screen Brightness: $brightness"

        val canWrite = Settings.System.canWrite(this)

        // If app has no permission to write system settings
        if (!canWrite) {
            seekBar.isEnabled = false
            allowWritePermission()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = "Screen Brightness : $progress"
                setBrightness(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun allowWritePermission() {
        val intent = Intent(
            Settings.ACTION_MANAGE_WRITE_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivity(intent)
    }

    private fun getBrightness(): Int {
        return Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 0)
    }

    fun setBrightness(value: Int) {
        Settings.System.putInt(
            this.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            value
        )
    }
}