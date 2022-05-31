package com.biorithm.helloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.github.cdimascio.dotenv.Dotenv

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputStream = classLoader.getResourceAsStream("env")
        Log.e(TAG, "onCreate: $inputStream")
        val env = Dotenv.configure()
            .inputStream(inputStream)
            .load()
        Log.e(TAG, "onCreate: ${env.get("APP_USERNAME", "username")}")
        Log.e(TAG, "onCreate: ${env.get("APP_PASSWORD")}")
    }
}