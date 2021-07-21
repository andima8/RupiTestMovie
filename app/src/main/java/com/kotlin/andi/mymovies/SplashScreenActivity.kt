package com.kotlin.andi.mymovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.andi.mymovies.databinding.ActivitySplashScreenBinding
import com.kotlin.andi.mymovies.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        const val A = 3
        const val B = 1000
    }

    private val splashScope = CoroutineScope(Dispatchers.Main)
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Start Animation
        binding.spAnimation.playAnimation()
        splashScope.launch(Dispatchers.IO) {
            for (i in 0 until A) {
                Thread.sleep(B.toLong())
            }
            startActivity()
        }
    }

    private fun startActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        splashScope.cancel()
        super.onPause()
    }
}
