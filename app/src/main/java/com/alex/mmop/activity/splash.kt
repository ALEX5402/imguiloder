package com.alex.mmop.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alex.mmop.R
import com.alex.mmop.ui.theme.ImguiloderTheme

class splash : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImguiloderTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    splashscreen()
                    startnect(this)
                }
            }

        }

    }
    fun startnect( context : Context){
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            context.startActivity(Intent(this,Login::class.java))
            finish()
        },3000)

    }
}


@SuppressLint("ComposableNaming", "UnrememberedMutableState")
@Composable
fun splashscreen( ){
    val isplayin by remember {
        mutableStateOf(true)
    }

    val compisition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animate))

    val state by animateLottieCompositionAsState(composition =compisition,
        isPlaying = isplayin,
        iterations = LottieConstants.IterateForever
    )
    Box {
    }
    LottieAnimation(composition = compisition, progress = state)

}

