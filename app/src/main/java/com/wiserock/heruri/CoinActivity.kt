package com.wiserock.heruri

import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coin.*
import kotlin.math.cos

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        val accelerateInterpolator = object : AccelerateInterpolator() {
            override fun getInterpolation(input: Float): Float =
                (cos((input + 1) * Math.PI) / 2.0f).toFloat() + 0.5f
        }
        activity_coin_button.setOnClickListener {
            activity_coin_coin.animate().setDuration(800).setInterpolator(accelerateInterpolator)
                .translationX(300f).translationY(400f)
                .scaleX(0f).scaleY(0f)
                .withEndAction {
                    activity_coin_coin.translationX = 0f
                    activity_coin_coin.translationY = 0f
                    activity_coin_coin.scaleX = 1f
                    activity_coin_coin.scaleY = 1f
                }.start()
        }
        activity_coin_button_back.setOnClickListener {
            finish()
        }
    }
}