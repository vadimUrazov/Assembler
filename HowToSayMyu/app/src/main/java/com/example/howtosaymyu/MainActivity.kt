package com.example.howtosaymyu

import androidx.appcompat.app.AppCompatActivity
import android.media.SoundPool
import android.content.res.AssetManager
import android.os.Bundle
import com.example.howtosaymyu.R
import android.widget.ImageButton
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.annotation.TargetApi
import android.os.Build
import android.media.AudioAttributes
import android.media.AudioManager
import android.content.res.AssetFileDescriptor
import android.view.View
import android.widget.Toast
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var mSoundPool: SoundPool? = null
    private var mAssetManager: AssetManager? = null
    private var mCatSound = 0
    private var mChickenSound = 0
    private var mCowSound = 0
    private var mDogSound = 0
    private var mDuckSound = 0
    private var mSheepSound = 0
    private var mStreamID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            // Для устройств до Android 5
//            createOldSoundPool();
//        } else {
//            // Для новых устройств
//            createNewSoundPool();
//        }

//        mAssetManager = getAssets();
//
//        // получим идентификаторы
//        mCatSound = loadSound("cat.ogg");
//        mChickenSound = loadSound("chicken.ogg");
//        mCowSound = loadSound("cow.ogg");
//        mDogSound = loadSound("dog.ogg");
//        mDuckSound = loadSound("duck.ogg");
//        mSheepSound = loadSound("sheep.ogg");
        val cowImageButton = findViewById<View>(R.id.imageButtonCow) as ImageButton
        //        cowImageButton.setOnClickListener(onClickListener);
        val chickenImageButton = findViewById<View>(R.id.imageButtonChicken) as ImageButton
        chickenImageButton.setOnClickListener(onClickListener)
        val catImageButton = findViewById<View>(R.id.imageButtonCat) as ImageButton
        catImageButton.setOnClickListener(onClickListener)
        val duckImageButton = findViewById<View>(R.id.imageButtonDuck) as ImageButton
        duckImageButton.setOnClickListener(onClickListener)
        val sheepImageButton = findViewById<View>(R.id.imageButtonSheep) as ImageButton
        sheepImageButton.setOnClickListener(onClickListener)
        val dogImageButton = findViewById<View>(R.id.imageButtonDog) as ImageButton
        dogImageButton.setOnClickListener(onClickListener)
        cowImageButton.setOnTouchListener { v, event ->
            val eventAction = event.action
            if (eventAction == MotionEvent.ACTION_UP) {
                // Отпускаем палец
                if (mStreamID > 0) mSoundPool!!.stop(mStreamID)
            }
            if (eventAction == MotionEvent.ACTION_DOWN) {
                // Нажимаем на кнопку
                mStreamID = playSound(mCowSound)
            }
            if (event.action == MotionEvent.ACTION_CANCEL) {
                mSoundPool!!.stop(mStreamID)
            }
            true
        }
    }

    var onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.imageButtonCow -> playSound(mCowSound)
            R.id.imageButtonChicken -> playSound(mChickenSound)
            R.id.imageButtonCat -> playSound(mCatSound)
            R.id.imageButtonDuck -> playSound(mDuckSound)
            R.id.imageButtonSheep -> playSound(mSheepSound)
            R.id.imageButtonDog -> playSound(mDogSound)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createNewSoundPool() {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        mSoundPool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()
    }

    private fun createOldSoundPool() {
        mSoundPool = SoundPool(3, AudioManager.STREAM_MUSIC, 0)
    }

    private fun playSound(sound: Int): Int {
        if (sound > 0) {
            mStreamID = mSoundPool!!.play(sound, 1f, 1f, 1, 0, 1f)
        }
        return mStreamID
    }

    private fun loadSound(fileName: String): Int {
        val afd: AssetFileDescriptor
        afd = try {
            mAssetManager!!.openFd(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(
                applicationContext, "Не могу загрузить файл $fileName",
                Toast.LENGTH_SHORT
            ).show()
            return -1
        }
        return mSoundPool!!.load(afd, 1)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool()
        } else {
            // Для новых устройств
            createNewSoundPool()
        }
        mAssetManager = assets

        // получим идентификаторы
        mCatSound = loadSound("cat.ogg")
        mChickenSound = loadSound("chicken.ogg")
        mCowSound = loadSound("cow.ogg")
        mDogSound = loadSound("dog.ogg")
        mDuckSound = loadSound("duck.ogg")
        mSheepSound = loadSound("sheep.ogg")
    }

    override fun onPause() {
        super.onPause()
        mSoundPool!!.release()
        mSoundPool = null
    }
}